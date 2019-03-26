import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Hashtable;
import java.util.Stack;

public class Listener extends scannerBaseListener {
    Hashtable<String, String[][]> table = new Hashtable<String, String[][]>();
    Stack<String> stack = new Stack<String>();
    int blockNum = 0;

    // private void arrayHelper(String key, String[] entry) {
    // String[][] current = table.get(key);
    // int len = current.length;
    // String[][] temp = new String[len + 1];

    // for (int i = 0; i < len - 1; i++) {
    // temp[i] = current[i];
    // }
    // temp[len] = entry;

    // table.replace(key, temp);
    // }

    private void declHelper(scannerParser.DeclContext context) {
        while (context.decl() != null) {
            if (context.string_decl() != null) {
                scannerParser.String_declContext stringContext = context.string_decl();

                // String[] entry = { stringContext.id().IDENTIFIER(), "STRING",
                // stringContext.str().STRINGLITERAL() };
                // arrayHelper(key, entry);

                System.out.println("name " + stringContext.id().IDENTIFIER() + " type STRING value "
                        + stringContext.str().STRINGLITERAL());
            } else if (context.var_decl() != null) {
                scannerParser.Var_declContext varContext = context.var_decl();
                String type = varContext.var_type().getText();
                scannerParser.Id_listContext list = varContext.id_list();

                // String[] entry = { list.id().IDENTIFIER(), type };
                // arrayHelper(key, entry);

                System.out.println("name " + list.id().IDENTIFIER() + " type " + type);

                scannerParser.Id_tailContext next = list.id_tail();

                while (next.id_tail() != null) {
                    // entry = new String[] { list.id().IDENTIFIER(), type };
                    // arrayHelper(key, entry);

                    System.out.println("name " + next.id().IDENTIFIER() + " type " + type);
                    next = next.id_tail();
                }
            }
            context = context.decl();
        }
    }

    @Override
    public void enterFunc_decl(scannerParser.Func_declContext ctx) {
        // stack.push(ctx.id().IDENTIFIER());
        System.out.println(ctx.id().IDENTIFIER());

        scannerParser.Param_decl_listContext list = ctx.param_decl_list();

        if (list.param_decl() != null) {
            scannerParser.Param_declContext parameter = list.param_decl();

            System.out.println("name " + parameter.id().IDENTIFIER() + " type " + parameter.var_type().getText());

            scannerParser.Param_decl_tailContext next = list.param_decl_tail();

            while (next.param_decl_tail() != null) {
                System.out.println("name " + next.param_decl().id().IDENTIFIER() + " type "
                        + next.param_decl().var_type().getText());
                next = next.param_decl_tail();
            }
        }

        declHelper(ctx.func_body().decl());
    }

    @Override
    public void enterProgram(scannerParser.ProgramContext ctx) {
        // stack.push("GLOBAL");

        scannerParser.DeclContext context = ctx.pgm_body().decl();

        System.out.println("GLOBAL");

        declHelper(context);
    }

    @Override
    public void enterIf_stmt(scannerParser.If_stmtContext ctx) {
        blockNum++;

        // stack.push("BLOCK " + blockNum);

        System.out.println("Block " + blockNum);

        declHelper(ctx.decl());
    }

    @Override
    public void enterElse_part(scannerParser.Else_partContext ctx) {
        if (ctx.decl() != null) {
            blockNum++;

            // stack.push("BLOCK " + blockNum);

            System.out.println("Block " + blockNum);

            declHelper(ctx.decl());
        }
    }

    @Override
    public void enterWhile_stmt(scannerParser.While_stmtContext ctx) {
        blockNum++;

        // stack.push("BLOCK " + blockNum);

        System.out.println("Block " + blockNum);

        declHelper(ctx.decl());
    }

    public Hashtable<String, String[][]> getSymbolTable() {
        return table;
    }

}