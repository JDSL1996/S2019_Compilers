public class ASTNode {
    private String payload;
    private ASTNode right;
    private ASTNode left;

    public ASTNode(String inPay) {
        payload = inPay;
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
            line += left.getPay();
            children = true;
        }
        if (right != null) {
            line += "    ".repeat(level) + right.getPay();
            line += right.printLeftAndRight(level + 1);
            children = true;
        }
        if (!children) {
            return tree;
        }
        return tree + line;
    }
}