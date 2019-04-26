import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Listener extends scannerBaseListener {
    private ASTNode addop;
    private ASTNode mulop;
    private ASTNode leaf;
    private ASTNode head;
    private ASTNode currentTop;
    private LinkedHashMap<String, String[][]> table = new LinkedHashMap<String, String[][]>();
    private ArrayList<String> while_start_tracker = new ArrayList();
    private ArrayList<String> while_end_tracker = new ArrayList();
    private ArrayList<String> if_start_tracker = new ArrayList();
    private ArrayList<String> if_end_tracker = new ArrayList();
    private ArrayList<String> else_tracker = new ArrayList();
    private ArrayList<Boolean> if_else_tracker = new ArrayList();//If else is present
    private ArrayList<Boolean> if_if_else_tracker = new ArrayList();//If else is present, for if tracking
    private int ifNum = 0;
    private int blockNum = 0;
    private int whileNum = 0;
    private int elseNum = 0;
    public static int tempCount = 1;
    private StringJoiner generated_code = new StringJoiner("\n");
    private HashSet<String> variables = new HashSet<>();
    private HashSet<String> used_variables = new HashSet<>();
    // copies the old array into a temp array to be stored in the outer array in the
    // hashmap value matching the key
    private void arrayHelper(String key, String[] entry) {
        String[][] current = table.get(key);
        // if there is already an array linked with this key
        if (current != null) {
            int len = current.length;
            String[][] temp = new String[len + 1][];
            variables = new HashSet<>();
            // copy the array
            for (int i = 0; i < len; i++) {
                if (entry == null) {
                    temp[i] = current[i];
                    return;
                }
                // if this location has information check for matching names
                else if (current[i] != null) {
                    if (variables.contains(current[i][0])) {
                        // if a match is found throw an error and end
                        System.out.println("DECLARATION ERROR " + current[i][0]);
                        System.exit(1);
                    }
                    variables.add(current[i][0]);
                    temp[i] = current[i];
                }
            }
            variables.clear();
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

                    String[] entry = {stringContext.id().IDENTIFIER().getText(), "STRING",
                    stringContext.str().STRINGLITERAL().getText()};
                    arrayHelper(key, entry);

                }
                // or a var-type
                else if (context.var_decl() != null) {
                    scannerParser.Var_declContext varContext = context.var_decl();
                    String type = varContext.var_type().getText();
                    scannerParser.Id_listContext list = varContext.id_list();

                    String[] entry = {list.id().IDENTIFIER().getText(), type};
                    arrayHelper(key, entry);

                    scannerParser.Id_tailContext next = list.id_tail();

                    while (next.id_tail() != null) {
                        entry = new String[]{next.id().IDENTIFIER().getText(), type};
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
//=====================================================CONDITIONALS=====================================================
    //Enter statements

    @Override
    public void enterIf_stmt(scannerParser.If_stmtContext ctx) {
        String operator = ctx.cond().compop().getText();
        String[] ops = ctx.cond().getText().split(operator);
        String OP1 = ops[0];
        String OP2 = ops[1];
        //Inverse logic because we are deciding when to jump
        if(operator.equals("=")){
            operator = "NE";
        }else if(operator.equals(">")){
            operator = "LE";
        }else if(operator.equals(">=")){
            operator = "LT";
        }else if(operator.equals("<")){
            operator = "GE";
        }else if(operator.equals("<=")){
            operator = "GT";
        }else if(operator.equals("!=")){
            operator = "EQ";
        }
        if(ctx.else_part() != null){
            if(!ctx.else_part().getText().equals("")){
                if_else_tracker.add(true);
                if_if_else_tracker.add(true);
                else_tracker.add("else_" + elseNum);
                if_start_tracker.add("if_" + ifNum);
                if_end_tracker.add("if_" + ifNum);
                generated_code.add(operator + " " + OP1 + " " + OP2 + " " + "else_" + elseNum);
                generated_code.add(operator + " " + OP1 + " " + OP2 + " " + "if_" + ifNum);
                ifNum++;
                elseNum++;
            }else{
                if_if_else_tracker.add(false);
                if_start_tracker.add("if_" + ifNum);
                if_else_tracker.add(false);
                generated_code.add(operator + " " + OP1 + " " + OP2 + " " + "if_" + ifNum);
                ifNum++;
            }
        }
        blockNum++;
    }
    @Override
    public void enterElse_part(scannerParser.Else_partContext ctx) {
        if(if_else_tracker.remove(if_else_tracker.size()-1)){
            generated_code.add("jmp " + if_end_tracker.remove(if_end_tracker.size()-1));
            generated_code.add("label " + else_tracker.remove(else_tracker.size()-1));
        }
    }
    @Override
    public void enterWhile_stmt(scannerParser.While_stmtContext ctx) {
        String operator = ctx.cond().compop().getText();
        String[] ops = ctx.cond().getText().split(operator);
        String OP1 = ops[0];
        String OP2 = ops[1];
        //Inverse logic because we are deciding when to jump
        if(operator.equals("=")){
            operator = "NE";
        }else if(operator.equals(">")){
            operator = "LE";
        }else if(operator.equals(">=")){
            operator = "LT";
        }else if(operator.equals("<")){
            operator = "GE";
        }else if(operator.equals("<=")){
            operator = "GT";
        }else if(operator.equals("!=")){
            operator = "EQ";
        }
        while_start_tracker.add("while_start_" + blockNum);
        generated_code.add("label while_start_" + blockNum);
        while_end_tracker.add("while_" + whileNum);
        generated_code.add(operator + " " + OP1 + " " + OP2 + " " + "while_" + whileNum);
        whileNum++;
        blockNum++;
    }
    //exit statements
    // add the key and values on if exit
    @Override
    public void exitIf_stmt(scannerParser.If_stmtContext ctx) {
        generated_code.add("label " + if_start_tracker.remove(if_start_tracker.size() - 1));
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
        generated_code.add("jmp " + while_start_tracker.remove(while_start_tracker.size()-1));
        generated_code.add("label " + while_end_tracker.remove(while_end_tracker.size()-1));
        blockNum++;
        String key = "BLOCK " + blockNum;

        declHelper(key, ctx.decl());
    }


    //=================================================End of: CONDITIONALS=================================================
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
    public void exitPostfix_expr(scannerParser.Postfix_exprContext ctx) {
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
    public ASTNode startTree(String context){
        String[] l_r = context.split(":=");
        ASTNode assignment = new ASTNode(":=", "assignment");
        ASTNode left = new ASTNode(l_r[0],"leaf");
        assignment.setLeft(left);
        if(l_r[1].length() > 0){
            System.out.println(l_r[1]);
            ArrayList<String> postfix = infixToPostfix(l_r[1]);
            for(String k: postfix){
                System.out.print(k + ",");
            }
            ASTNode root = evaluatePostfix(postfix);
            while(root.getParent() != null){
                root = root.getParent();
            }
            System.out.println("NEW TREE");
            System.out.println(root.printLeftAndRight(1));
            System.out.println("TREE END");
            assignment.setRight(root);
        }
        return assignment;
    }
    public int Prec(char ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    // The main method that converts given infix expression
    // to postfix expression.
    public ArrayList<String> infixToPostfix(String exp)
    {

        // initializing empty String for result
        ArrayList<String> result = new ArrayList<>();

        // initializing empty stack
        Stack<Character> stack = new Stack<>();
        String tempResult = "";
        for (int i = 0; i<exp.length(); ++i)
        {
            char c = exp.charAt(i);

            // If the scanned character is an operand, add it to output.
            if("abcdefghijklmnopqrstuvwxyz0123456789.,".contains(Character.toString(c))) {

                tempResult += c;
                if(i+1<exp.length()){
                    if(!"abcdefghijklmnopqrstuvwxyz0123456789.,".contains((Character.toString(exp.charAt(i+1))))){
                        result.add(tempResult);
                        tempResult = "";
                    }
                }else{
                    result.add(tempResult);
                    tempResult = "";
                    continue;
                }

            }
            // If the scanned character is an '(', push it to the stack.
            else if (c == '('){

                stack.push(c);

            }


                //  If the scanned character is an ')', pop and output from the stack
                // until an '(' is encountered.
            else if (c == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '('){

                    result.add(Character.toString(stack.pop()));

                }

                if (!stack.isEmpty() && stack.peek() != '(')
                    return null; // invalid expression
                else if(!stack.isEmpty()){
                    stack.pop();
                }

            }
            else // an operator is encountered
            {

                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())) {
                    result.add(Character.toString(stack.pop()));

                }
                stack.push(c);
            }

        }

        // pop all the operators from the stack
        while (!stack.isEmpty()) {

            result.add(Character.toString(stack.pop()));

        }
        return result;
    }
    private ASTNode evaluatePostfix(ArrayList<String> exp)
    {
        //create a stack
        Stack<ASTNode> stack=new Stack<>();

        // Scan all characters one by one
        for(int i=0;i<exp.size();i++)
        {
            String chunk = exp.get(i);
            // If the scanned character is an operand (number here),
            // push it to the stack.
            if(!(chunk.substring(0,1).equals("+") || chunk.substring(0,1).equals("-") || chunk.substring(0,1).equals("*") || chunk.substring(0,1).equals("/"))){
                stack.push(new ASTNode(chunk,"primary"));
            }
            else
            {
                ASTNode val1 = stack.pop();
                ASTNode val2 = stack.pop();
                if(chunk.substring(0,1).equals("+")){
                    ASTNode operator = new ASTNode(chunk,"ADDI");
                    operator.setRight(val1);
                    operator.setLeft(val2);
                    val2.setParent(operator);
                    val1.setParent(operator);
                    stack.push(operator);
                }else if(chunk.substring(0,1).equals("-")){
                    ASTNode operator = new ASTNode(chunk,"SUBI");
                    operator.setRight(val1);
                    operator.setLeft(val2);
                    stack.push(operator);
                    val2.setParent(operator);
                    val1.setParent(operator);
                }else if(chunk.substring(0,1).equals("/")){
                    ASTNode operator = new ASTNode(chunk,"DIVI");
                    operator.setRight(val1);
                    operator.setLeft(val2);
                    stack.push(operator);
                    val2.setParent(operator);
                    val1.setParent(operator);
                }else if(chunk.substring(0,1).equals("*")){
                    ASTNode operator = new ASTNode(chunk,"MULTI");
                    operator.setRight(val1);
                    operator.setLeft(val2);
                    stack.push(operator);
                    val2.setParent(operator);
                    val1.setParent(operator);
                }
            }
        }
        return stack.pop();
    }
    @Override
    public void enterAssign_expr(scannerParser.Assign_exprContext ctx) {
        head = startTree(ctx.getText());
        getPostOrder(head);
//        head = new ASTNode(":=","assignment");
//
//        head.setLeft(new ASTNode(ctx.id().getText(),"primary"));
        if(!used_variables.contains(ctx.id().getText())){
            used_variables.add(ctx.id().getText());
        }
        mulop = null;
        addop = null;
        currentTop = null;
    }
    @Override
    public void exitAssign_expr(scannerParser.Assign_exprContext ctx) {


        //System.out.println(head.printLeftAndRight(1));
        //generated_code.add(generateCode());
    }
    @Override
    public void exitWrite_stmt(scannerParser.Write_stmtContext ctx){
        String[] split = ctx.id_list().getText().split(",");
        for(int x = 0;x<split.length;x++){
            if(x%2 == 0){
                generated_code.add("WRITEI " + split[x]);
            }else{
                generated_code.add("WRITES " + split[x]);
            }

        }
    }
    @Override
    public void exitRead_stmt(scannerParser.Read_stmtContext ctx){
        String[] split = ctx.id_list().getText().split(",");
        if(split.length >= 1){
            generated_code.add("READI " + split[0]);
            used_variables.add(split[0]);
        }
        if(split.length >= 2){
            generated_code.add("READI " + split[1]);
            used_variables.add(split[1]);
        }

    }
    String prevTemp = null;
    public void getPostOrder(ASTNode node){
        if (node == null){
            return;
        }
        tempCount++;
        int localTemp = tempCount;

        getPostOrder(node.getLeft());

        // then recur on right subtree
        getPostOrder(node.getRight());
        if(node.getPay().equals("+") || node.getPay().equals("-") || node.getPay().equals("*")  || node.getPay().equals("/") ){
            node.setOP1(node.getLeft().getResult(), "OP");
            node.setOP2(node.getRight().getResult(), "OP");
            node.setResult("$T" + localTemp);
            prevTemp = "$T" + localTemp;

            generated_code.add(node.generateCode());
        }else if(node.getPay().equals(":=")){
            if(prevTemp != null){
                generated_code.add(";using previous temp");
                node.setOP2(node.getLeft().getPay(),"TEMP");
                node.setOP1(prevTemp,"TEMP");
                generated_code.add(node.generateCode());
                tempCount++;
            }else{
                generated_code.add(";Creating new temp");
                node.setOP1(node.getLeft().getPay(),"primary");
                node.setOP2(node.getRight().getResult(),"TEMP");
                node.setResult("$T" + tempCount);
                generated_code.add(node.generateCode());
                tempCount++;
            }
        }
    }
    public String generateCode(){
        prevTemp = null;
        getPostOrder(head);
        return "";
//        ASTNode temp_node = head;
//        //Go to the bottom right
//        while(temp_node.getRight()!= null){
//            temp_node = temp_node.getRight();
//        }
//        StringJoiner sj = new StringJoiner("\n");
//        String prevTemp = null;
//        while(temp_node.getParent() != null){
//            ASTNode op = temp_node.getParent();
//            if(prevTemp != null){
//                op.setOP1(prevTemp, "TEMP");
//                op.setOP2(op.getLeft().getPay(), op.getLeft().getInstruction());
//            }else{
//                op.setOP1(op.getLeft().getPay(), op.getLeft().getInstruction());
//                op.setOP2(temp_node.getPay(), temp_node.getInstruction());
//            }
//            op.setResult("$T" + tempCount);
//            prevTemp = "$T" + tempCount;
//            sj.add(op.generateCode());
//            temp_node = temp_node.getParent();
//            if(temp_node.getParent() != null){
//                tempCount++;
//            }
//        }
//        return sj.toString();
        
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
            System.out.println(line);
            String[] split = line.split(" ");
            if(split.length > 1){
                if(split[0].equals("STOREI")){
                    split[0] = "move";
                }
                System.out.println("!!!" + split[0]);
                if(split[1].substring(0,1).equals("$")){
                    split[1] = "r" + (Integer.parseInt(split[1].substring(2))-1);
                }
                if(split[0].equals("READI")){
                    split[0] = "sys readi";
                }
            }
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
                }else if(split[0].equals("NE")){
                    String r_alloc = "move " + split[2] + " r" + tempCount;
                    String comp = "cmpi " + split[1] + " r" + tempCount;
                    String jmp = "jne " + split[3];
                    tempCount++;
                    String tempSplit[] = new String[1];
                    tempSplit[0] = r_alloc + "\n" + comp + "\n" + jmp;
                    split = tempSplit;
                }else if(split[0].equals("LT")) {
                    String r_alloc = "move " + split[2] + " r" + tempCount;
                    String comp = "cmpi " + split[1] + " r" + tempCount;
                    String jmp = "jlt " + split[3];
                    tempCount++;
                    String tempSplit[] = new String[1];
                    tempSplit[0] = r_alloc + "\n" + comp + "\n" + jmp;
                    split = tempSplit;
                }else if(split[0].equals("LE")){
                    String r_alloc = "move " + split[2] + " r" + tempCount;
                    String comp = "cmpi " + split[1] + " r" + tempCount;
                    String jmp = "jle " + split[3];
                    tempCount++;
                    String tempSplit[] = new String[1];
                    tempSplit[0] = r_alloc + "\n" + comp + "\n" + jmp;
                    split = tempSplit;
                }else if(split[0].equals("GT")){
                    String r_alloc = "move " + split[2] + " r" + tempCount;
                    String comp = "cmpi " + split[1] + " r" + tempCount;
                    String jmp = "jgt " + split[3];
                    tempCount++;
                    String tempSplit[] = new String[1];
                    tempSplit[0] = r_alloc + "\n" + comp + "\n" + jmp;
                    split = tempSplit;
                }else if(split[0].equals("GE")){
                    String r_alloc = "move " + split[2] + " r" + tempCount;
                    String comp = "cmpi " + split[1] + " r" + tempCount;
                    String jmp = "jge " + split[3];
                    tempCount++;
                    String tempSplit[] = new String[1];
                    tempSplit[0] = r_alloc + "\n" + comp + "\n" + jmp;
                    split = tempSplit;
                }else if(split[0].equals("EQ")){
                    String r_alloc = "move " + split[2] + " r" + tempCount;
                    String comp = "cmpi " + split[1] + " r" + tempCount;
                    String jmp = "jeq " + split[3];
                    tempCount++;
                    String tempSplit[] = new String[1];
                    tempSplit[0] = r_alloc + "\n" + comp + "\n" + jmp;
                    split = tempSplit;
                }
            }
            sj.add(String.join(" ",split));
        }
        sj.add("sys halt\n");
        System.out.println(generated_code.toString());

        for(String variable : used_variables) {
            System.out.println("var " + variable);
        }
        System.out.println("str newline \"\\n\"");
        System.out.println("label main");
        System.out.println(sj.toString());
        declHelper(key, context);
    }
    // return the table
    public LinkedHashMap<String, String[][]> getSymbolTable() {
        return table;
    }
}