package been;

public class TreeNode {
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val) {
        this.val=val;
    }


    public TreeNode setVal(int val){
        this.val=val;
        return this;
    }

    public TreeNode setLeft(TreeNode root){
        this.left=root;
        return this;
    }

    public TreeNode setRight(TreeNode root){
        this.right=root;
        return this;
    }
}
