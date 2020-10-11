class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return mergeTreeImpl(t1, t2);
    }
    
    private TreeNode mergeTreeImpl(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTreeImpl(t1.left, t2.left);
        t1.right = mergeTreeImpl(t1.right, t2.right);
        return t1;
    }
}
