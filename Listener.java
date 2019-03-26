import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.LinkedHashMap;
import java.util.HashSet;

public class Listener extends scannerBaseListener {
    LinkedHashMap<String, String[][]> table = new LinkedHashMap<String, String[][]>();
    int blockNum = 0;

    private void arrayHelper(String key, String[] entry) {
        String[][] current = table.get(key);
        if (current != null) {
            int len = current.length;
            String[][] temp = new String[len + 1][];
            HashSet<String> duplicateCheck = new HashSet<>();
            for (int i = 0; i < len; i++) {
                if (entry == null){
                    temp[i] = current[i];
                    return;
                }
                else if(current[i] != null){
                    duplicateCheck.contains(current[i][0]);
                    if(duplicateCheck.contains(current[i][0])){
                       System.out.println("DECLARATION ERROR " + current[i][0]);
                       System.exit(1);
                    }
                    duplicateCheck.add(current[i][0]);
                    temp[i] = current[i];
                }
            }
            temp[len] = entry;

            table.replace(key, temp);
        } else {
            table.put(key, new String[][] { entry });
        }
    }

    private void declHelper(String key, scannerParser.DeclContext context) {
        if(context != null && context.decl() != null){
            while (context.decl() != null) {
                if (context.string_decl() != null) {
                    scannerParser.String_declContext stringContext = context.string_decl();
    
                    String[] entry = { stringContext.id().IDENTIFIER().getText(), "STRING",
                            stringContext.str().STRINGLITERAL().getText() };
                    arrayHelper(key, entry);

                } else if (context.var_decl() != null) {
                    scannerParser.Var_declContext varContext = context.var_decl();
                    String type = varContext.var_type().getText();
                    scannerParser.Id_listContext list = varContext.id_list();
    
                    String[] entry = { list.id().IDENTIFIER().getText(), type };
                    arrayHelper(key, entry);
    
                    scannerParser.Id_tailContext next = list.id_tail();
    
                    while (next.id_tail() != null) {
                        entry = new String[] { next.id().IDENTIFIER().getText(), type };
                        arrayHelper(key, entry);

                        next = next.id_tail();
                    }
                }
                context = context.decl();
            }
        }else{
            arrayHelper(key, null);
        }
    }

    @Override
    public void enterFunc_decl(scannerParser.Func_declContext ctx) {
        String key = ctx.id().IDENTIFIER().getText();
        arrayHelper(key, null);
    }

    @Override
    public void exitFunc_decl(scannerParser.Func_declContext ctx) {
        String key = ctx.id().IDENTIFIER().getText();

        scannerParser.Param_decl_listContext list = ctx.param_decl_list();

        if (list != null) {
            if (list.param_decl() != null) {
                scannerParser.Param_declContext parameter = list.param_decl();

                String[] entry = { parameter.id().IDENTIFIER().getText(), parameter.var_type().getText() };
                arrayHelper(key, entry);

                scannerParser.Param_decl_tailContext next = list.param_decl_tail();

                while (next.param_decl_tail() != null) {
                    entry = new String[] { next.param_decl().id().IDENTIFIER().getText(),
                            next.param_decl().var_type().getText() };
                    arrayHelper(key, entry);
                    next = next.param_decl_tail();
                }
            }
        }
        declHelper(key, ctx.func_body().decl());
    }

    @Override
    public void enterProgram(scannerParser.ProgramContext ctx) {
        String key = "GLOBAL";
        arrayHelper(key, null);
    }

    @Override
    public void exitProgram(scannerParser.ProgramContext ctx) {
        String key = "GLOBAL";
        scannerParser.DeclContext context = ctx.pgm_body().decl();

        declHelper(key, context);
    }

    // @Override
    // public void enterIf_stmt(scannerParser.If_stmtContext ctx) {
    //     blockNum++;

    //     String key = "BLOCK " + blockNum;
    //     arrayHelper(key, null);
    // }

    @Override
    public void exitIf_stmt(scannerParser.If_stmtContext ctx) {
        blockNum++;
        String key = "BLOCK " + blockNum;

        declHelper(key, ctx.decl());
    }

    // @Override
    // public void enterElse_part(scannerParser.Else_partContext ctx) {
    //     blockNum++;
    //     String key = "BLOCK " + blockNum;
    //     arrayHelper(key, null);
    // }

    @Override
    public void exitElse_part(scannerParser.Else_partContext ctx) {
        if(ctx.decl() != null){
            blockNum++;
            
            String key = "BLOCK " + blockNum;
            
            declHelper(key, ctx.decl());
        }
    }

    // @Override
    // public void enterWhile_stmt(scannerParser.While_stmtContext ctx) {
    //     blockNum++;

    //     String key = "BLOCK " + blockNum;
    //     arrayHelper(key, null);
    // }

    @Override
    public void exitWhile_stmt(scannerParser.While_stmtContext ctx) {
        blockNum++;
        
        String key = "BLOCK " + blockNum;

        declHelper(key, ctx.decl());
    }

    public LinkedHashMap<String, String[][]> getSymbolTable() {
        return table;
    }
}