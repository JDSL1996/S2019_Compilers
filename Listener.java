import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.LinkedHashMap;
import java.util.Stack;
import java.util.HashSet;
import java.util.StringJoiner; 

public class Listener extends scannerBaseListener {
    private ASTNode addop;
    private ASTNode mulop;
    private ASTNode leaf;
    private ASTNode head;
    private ASTNode currentOp;
    private ASTNode currentTop;
    private LinkedHashMap<String, String[][]> table = new LinkedHashMap<String, String[][]>();
    private int blockNum = 0;
    public static int tempCount = 1;
    private StringJoiner generated_code = new StringJoiner("\n");
    // copies the old array into a temp array to be stored in the outer array in the
    // hashmap value matching the key
    private void arrayHelper(String key, String[] entry) {
        String[][] current = table.get(key);
        // if there is already an array linked with this key
        if (current != null) {
            int len = current.length;
            String[][] temp = new String[len + 1][];
            HashSet<String> duplicateCheck = new HashSet<>();
            // copy the array
            for (int i = 0; i < len; i++) {
                if (entry == null) {
                    temp[i] = current[i];
                    return;
                }
                // if this location has information check for matching names
                else if (current[i] != null) {
                    duplicateCheck.contains(current[i][0]);
                    if (duplicateCheck.contains(current[i][0])) {
                        // if a match is found throw an error and end
                        System.out.println("DECLARATION ERROR " + current[i][0]);
                        System.exit(1);
                    }
                    duplicateCheck.add(current[i][0]);
                    temp[i] = current[i];
                }
            }
            // add the new entry
            temp[len] = entry;

            // link the new array structure to the key
            table.replace(key, temp);
        } else {
            table.put(key, new String[][] { entry });
        }
    }

    // for any decleration, add the key, entry pair to the map
    private void declHelper(String key, scannerParser.DeclContext context) {
        // if there is context to be added
        if (context != null && context.decl() != null) {
            // while there is still stuff to add
            while (context.decl() != null) {
                // if the stuff is a string
                if (context.string_decl() != null) {
                    scannerParser.String_declContext stringContext = context.string_decl();

                    String[] entry = { stringContext.id().IDENTIFIER().getText(), "STRING",
                            stringContext.str().STRINGLITERAL().getText() };
                    arrayHelper(key, entry);

                }
                // or a var-type
                else if (context.var_decl() != null) {
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
        }
        // otherwise just add the key
        else {
            arrayHelper(key, null);
        }
    }

    @Override
    public void enterAddop(scannerParser.AddopContext ctx) {
        if (currentTop != null) {
            ASTNode temp;
            if(ctx.getText().equals("+")){
                temp = new ASTNode(ctx.getText(), "ADDI");
            }else{
                temp = new ASTNode(ctx.getText(), "SUBI");
            }
            temp.setRight(currentTop);
            temp.setLeft(leaf);
            currentTop.setParent(temp);
            currentTop = temp;
        } else {
            if(ctx.getText().equals("+")){
                addop = new ASTNode(ctx.getText(), "ADDI");
            }else{
                addop = new ASTNode(ctx.getText(), "SUBI");
            }
            addop.setLeft(leaf);
            currentTop = addop;
        }
    }
    @Override
    public void enterMulop(scannerParser.MulopContext ctx) {
        if (currentTop != null) {
            ASTNode temp;
            if(ctx.getText().equals("*")){
                temp = new ASTNode(ctx.getText(), "MULTI");
            }else{
                temp = new ASTNode(ctx.getText(), "DIVI");
            }
            temp.setRight(currentTop);
            temp.setLeft(leaf);
            currentTop.setParent(temp);
            currentTop = temp;
        } else {
            if(ctx.getText().equals("*")){
                mulop = new ASTNode(ctx.getText(), "MULTI");
            }else{
                mulop = new ASTNode(ctx.getText(), "DIVI");
            }
            mulop.setLeft(leaf);
            currentTop = mulop;
        }
    }
    @Override
    public void enterPostfix_expr(scannerParser.Postfix_exprContext ctx) {
        if (ctx.primary() != null) {
            leaf = new ASTNode(ctx.primary().getText(),"primary");
            //System.out.println(leaf.getPay());
            boolean first = false;
            if(addop != null){
                first = true;
                addop.setRight(leaf);
                leaf.setParent(addop);
                addop = null;
            }else if(mulop != null){
                first = true;
                mulop.setRight(leaf);
                leaf.setParent(mulop);
                mulop = null;
            }

            // subtree of single operation
            if (currentTop != null && !first) {
                currentTop.setLeft(leaf);
                leaf.setParent(currentTop);
            }

        }
    }

    @Override
    public void enterAssign_expr(scannerParser.Assign_exprContext ctx) {
        head = new ASTNode(":=","assignment");
        head.setLeft(new ASTNode(ctx.id().getText(),"primary"));
        mulop = null;
        addop = null;
        currentTop = null;
    }

    @Override
    public void exitWrite_stmt(scannerParser.Write_stmtContext ctx){
        String[] split = ctx.id_list().getText().split(",");
        if(split.length == 1){
            generated_code.add("WRITEI " + split[0]);
        }else{
            generated_code.add("WRITEI " + split[0] + "\nWRITES " + split[1]);        
        }
    }
    @Override
    public void exitRead_stmt(scannerParser.Read_stmtContext ctx){
        String[] split = ctx.id_list().getText().split(",");
        generated_code.add("READI " + split[0]);
    }
    @Override
    public void exitAssign_expr(scannerParser.Assign_exprContext ctx) {
        if (currentTop != null) {
            head.setRight(currentTop);
            currentTop.setParent(head);
            currentTop = null;
        } else {
            ASTNode temp = new ASTNode(ctx.expr().factor().postfix_expr().primary().getText(),"primary");
            head.setRight(temp);
            temp.setParent(head);
        }
        //System.out.println(head.printLeftAndRight(1));
        generated_code.add(generateCode());
    }
    public String generateCode(){
        ASTNode temp_node = head;
        //Go to the bottom right
        while(temp_node.getRight()!= null){
            temp_node = temp_node.getRight();
        }
        StringJoiner sj = new StringJoiner("\n");
        String prevTemp = null;
        while(temp_node.getParent() != null){
            ASTNode op = temp_node.getParent();
            if(prevTemp != null){
                op.setOP1(prevTemp, "TEMP");
                op.setOP2(op.getLeft().getPay(), op.getLeft().getInstruction());
            }else{
                op.setOP1(op.getLeft().getPay(), op.getLeft().getInstruction());
                op.setOP2(temp_node.getPay(), temp_node.getInstruction());
            }
            op.setResult("$T" + tempCount);
            prevTemp = "$T" + tempCount;
            sj.add(op.generateCode());
            temp_node = temp_node.getParent();
            if(temp_node.getParent() != null){
                tempCount++;
            }
        }
        return sj.toString();
        
    }
    // addop the key on function entry
    @Override
    public void enterFunc_decl(scannerParser.Func_declContext ctx) {
        String key = ctx.id().IDENTIFIER().getText();
        arrayHelper(key, null);
    }

    // add the values to the key on function exit
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

    // add the key on program entry
    @Override
    public void enterProgram(scannerParser.ProgramContext ctx) {
        String key = "GLOBAL";
        arrayHelper(key, null);
    }

    // add the values to the key on program exit
    @Override 
    public void exitProgram(scannerParser.ProgramContext ctx) {
        String key = "GLOBAL";
        scannerParser.DeclContext context = ctx.pgm_body().decl();
        String[] lines = generated_code.toString().split("\n");
        StringJoiner sj = new StringJoiner("\n");
        for(String line : lines){
            String[] split = line.split(" ");
            if(split.length > 1){
                if(split[0].equals("STOREI")){
                    split[0] = "move";
                }
                if(split[1].substring(0,1).equals("$")){
                    split[1] = "r" + (Integer.parseInt(split[1].substring(2))-1);
                }
                if(split[0].equals("READI")){
                    split[0] = "sys readi";
                }
            }
            //;WRITEI e
            //;WRITES newline
            
            //sys writei c
            //sys writes newline
            if(split.length == 2){
                if(split[0].equals("WRITEI")){
                    split[0] = "sys writei";
                }else if(split[0].equals("WRITES")){
                    split[0] = "sys writes";
                }
            }
            if(split.length > 2){
                
                if(split[2].substring(0,1).equals("$")){
                    split[2] = "r" + (Integer.parseInt(split[2].substring(2))-1);
                }
            }
            if(split.length > 3){
                if(split[3].substring(0,1).equals("$")){
                    split[3] = "r" + (Integer.parseInt(split[3].substring(2))-1);
                }else{
                    //TODO: grab variable name and add to a hash list
                }
                if(split[0].equals("ADDI")){
                    String[] tempSplit = new String[1];
                    tempSplit[0] = "move " + split[1] + " " + split[3] + "\naddi " + split[2] + " " + split[3];
                    split = tempSplit;
                }else if(split[0].equals("SUBI")){
                    String[] tempSplit = new String[1];
                    tempSplit[0] = "move " + split[1] + " " + split[3] + "\nsubi " + split[2] + " " + split[3];
                    split = tempSplit;
                }else if(split[0].equals("MULTI")){
                    String[] tempSplit = new String[1];
                    tempSplit[0] = "move " + split[1] + " " + split[3] + "\nmuli " + split[2] + " " + split[3];
                    split = tempSplit;
                }else if(split[0].equals("DIVI")){
                    String[] tempSplit = new String[1];
                    tempSplit[0] = "move " + split[1] + " " + split[3] + "\ndivi " + split[2] + " " + split[3];
                    split = tempSplit;
                }
            }
            sj.add(String.join(" ",split));
        }
        //System.out.println(generated_code.toString());
        System.out.println(sj.toString());
        declHelper(key, context);
    }

    // add the key and values on if exit
    @Override
    public void exitIf_stmt(scannerParser.If_stmtContext ctx) {
        blockNum++;
        String key = "BLOCK " + blockNum;

        declHelper(key, ctx.decl());
    }

    // add the key and values on else exit if there is an else
    @Override
    public void exitElse_part(scannerParser.Else_partContext ctx) {
        if (ctx.decl() != null) {
            blockNum++;

            String key = "BLOCK " + blockNum;

            declHelper(key, ctx.decl());
        }
    }

    // add the key and values on while exit
    @Override
    public void exitWhile_stmt(scannerParser.While_stmtContext ctx) {
        blockNum++;

        String key = "BLOCK " + blockNum;

        declHelper(key, ctx.decl());
    }

    // return the table
    public LinkedHashMap<String, String[][]> getSymbolTable() {
        return table;
    }
}