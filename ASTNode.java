public class ASTNode {
    private String payload;
    private ASTNode right;
    private ASTNode left;

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
}