class Solution {
    int sum = 0;
    private void compute(TreeNode root) {        
        if (root == null) {
            return;
        }
        if (root.right != null) {
            bstToGst(root.right);
        }
        root.val = root.val += this.sum;
        this.sum = root.val;
        if (root.left != null) {
            bstToGst(root.left);
        }
    }
    
    public TreeNode bstToGst(TreeNode root) {
        compute(root);
        return root;
    }
}
