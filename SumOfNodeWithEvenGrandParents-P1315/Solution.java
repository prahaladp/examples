class Solution {
    public int sum(TreeNode t, TreeNode p, TreeNode gp) {
        if (t == null) {
            return 0;
        }
        int s = 0;
        if (gp != null && gp.val % 2 == 0) {
            s += t.val;
        }
        return s + sum(t.left, t, p) + sum(t.right, t, p);
    }
    
    public int sumEvenGrandparent(TreeNode root) {
        return sum(root, null, null);
    }
}
