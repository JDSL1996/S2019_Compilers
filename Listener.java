import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Stack;

public class Listener extends scannerBaseListener {
    Hashtable<String, String[][]> table = new Hashtable<String, String[][]>();
    Stack<String> stack = new Stack<String>();
    int blockNum = 0;

    private void arrayHelper(String key, String[] entry) {
        System.out.println(key);
        String[][] current = table.get(key);
        if (current != null) {
            int len = current.length;
            String[][] temp = new String[len + 1][];

            for (int i = 0; i < len - 1; i++) {
                temp[i] = current[i];
            }
            temp[len] = entry;

            table.replace(key, temp);
        } else {
            table.put(key, new String[][] { entry });
        }
    }

    private void declHelper(String key, scannerParser.DeclContext context) {
        while (context.decl() != null) {
            if (context.string_decl() != null) {
                scannerParser.String_declContext stringContext = context.string_decl();

                String[] entry = { stringContext.id().IDENTIFIER().getText(), "STRING",
                        stringContext.str().STRINGLITERAL().getText() };
                arrayHelper(key, entry);

                // System.out.println("name " + stringContext.id().IDENTIFIER() + " type STRING
                // value "
                // + stringContext.str().STRINGLITERAL());
            } else if (context.var_decl() != null) {
                scannerParser.Var_declContext varContext = context.var_decl();
                String type = varContext.var_type().getText();
                scannerParser.Id_listContext list = varContext.id_list();

                String[] entry = { list.id().IDENTIFIER().getText(), type };
                arrayHelper(key, entry);

                // System.out.println("name " + list.id().IDENTIFIER() + " type " + type);

                scannerParser.Id_tailContext next = list.id_tail();

                while (next.id_tail() != null) {
                    entry = new String[] { next.id().IDENTIFIER().getText(), type };
                    arrayHelper(key, entry);

                    // System.out.println("name " + next.id().IDENTIFIER() + " type " + type);
                    next = next.id_tail();
                }
            }
            context = context.decl();
        }
    }

    @Override
    public void enterFunc_decl(scannerParser.Func_declContext ctx) {
        String key = ctx.id().IDENTIFIER().getText();
        stack.add(key);
    }

    @Override
    public void exitFunc_decl(scannerParser.Func_declContext ctx) {
        String key = stack.pop();

        scannerParser.Param_decl_listContext list = ctx.param_decl_list();

        if (list != null) {
            if (list.param_decl() != null) {
                scannerParser.Param_declContext parameter = list.param_decl();

                String[] entry = { parameter.id().IDENTIFIER().getText(), parameter.var_type().getText() };
                arrayHelper(key, entry);

                // System.out.println("name " + parameter.id().IDENTIFIER() + " type " +
                // parameter.var_type().getText());

                scannerParser.Param_decl_tailContext next = list.param_decl_tail();

                while (next.param_decl_tail() != null) {
                    entry = new String[] { next.param_decl().id().IDENTIFIER().getText(),
                            next.param_decl().var_type().getText() };
                    arrayHelper(key, entry);

                    // System.out.println("name " + next.param_decl().id().IDENTIFIER() + " type "
                    // + next.param_decl().var_type().getText());
                    next = next.param_decl_tail();
                }
            }
        }

        declHelper(key, ctx.func_body().decl());
    }

    @Override
    public void enterProgram(scannerParser.ProgramContext ctx) {
        String key = "GLOBAL";
        stack.add(key);
    }

    @Override
    public void exitProgram(scannerParser.ProgramContext ctx) {
        String key = stack.pop();
        scannerParser.DeclContext context = ctx.pgm_body().decl();

        declHelper(key, context);
    }

    @Override
    public void enterIf_stmt(scannerParser.If_stmtContext ctx) {
        blockNum++;

        String key = "BLOCK " + blockNum;
        stack.add(key);

        declHelper(key, ctx.decl());
    }

    @Override
    public void enterElse_part(scannerParser.Else_partContext ctx) {
        if (ctx.decl() != null) {
            blockNum++;

            String key = "BLOCK " + blockNum;
            stack.add(key);

            declHelper(key, ctx.decl());
        }
    }

    @Override
    public void enterWhile_stmt(scannerParser.While_stmtContext ctx) {
        blockNum++;

        String key = "BLOCK " + blockNum;
        stack.add(key);

        declHelper(key, ctx.decl());
    }

    public Hashtable<String, String[][]> getSymbolTable() {

        for (String key : table.keySet()) {
            System.out.println(key);
        }
        return table;
    }

    public Stack<String> getStack() {
        // System.out.println(stack.size());
        return stack;
    }

}