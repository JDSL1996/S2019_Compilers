public class ASTNode {
    private String payload;
    private ASTNode parent;
    private ASTNode right;
    private ASTNode left;
    private String[] code;
    private String temp = "";
    private String op1 = "";
    private String op2 = "";
    private String opType1 = "";
    private String opType2 = "";
    private String type = "";
    private String instruction;
    public ASTNode(String inPay, String instruction) {
        payload = inPay;
        this.instruction = instruction;
    }
    public void setOP1(String op, String opType1){
        op1 = op;
        this.opType1 = opType1;
    }
    public void setOP2(String op, String opType2){
        op2 = op;
        this.opType2 = opType2;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){return type;}
    public void setResult(String temp){
        this.temp = temp;
    }
    public String getResult(){
        if(instruction.equals("primary")){
            return payload;
        }else{
            return temp;//code[3]
        }
    }
    public String getInstruction(){
        return instruction;
    }
    public void setInstruction(String instruction){
        this.instruction = instruction;
    }
    public String generateCode(){
        if(instruction.equals("assignment")){
            if(opType1.equals("primary")){
                String first;
                String second;
                first = String.join(" ","STOREI",op2,temp) + "\n";
                second = String.join(" ","STOREI",temp,op1);
                return first + second;
            }else{
                return "STOREI " + op1 + " " + op2;
            }
        }else{
            return String.join(" ",instruction,op1,op2,temp);
        }
    }
    public void setPayload(String inPay) {
        payload = inPay;
    }
    public void setRight(ASTNode inRight) {
        right = inRight;
    }

    public void setLeft(ASTNode inLeft) {
        left = inLeft;
    }

    public void setParent(ASTNode parent){
        this.parent = parent;
    }
    public ASTNode getParent(){
        return parent;
    }
    public String getPay() {
        return payload;
    }

    public ASTNode getRight() {
        return right;
    }

    public ASTNode getLeft() {
        return left;
    }

    public String printLeftAndRight(int level) {
        String tree = "";
        if (level == 1) {
            tree = "   ".repeat(level) + payload;
        }
        String line = "\n" + "  ".repeat(level) + "/~" + "\\\n";
        boolean children = false;
        if (left != null) {
            line += "  ".repeat(level) + left.getPay();
            line += left.printLeftAndRight(level + 1);
            children = true;
        }
        if (right != null) {
            line += "   " + right.getPay();
            line += right.printLeftAndRight(level + 1);
            children = true;
        }
        if (!children) {
            return tree;
        }
        return tree + line;
    }
}