class Solution {
    int minDep = Integer.MAX_VALUE;
    private void traverse(TreeNode root, int dep) {
        if (root==null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (dep < this.minDep) {
                this.minDep = dep;
            }
            return;
        }
        traverse(root.left,dep+1);
        traverse(root.right,dep+1);
    }
    
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //this.minDep = 1;
        traverse(root, 1);
        return this.minDep;
    }
}
