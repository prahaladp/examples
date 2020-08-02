class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) {
                return root;
            }
            
            if (val > root.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return root;
    }
}
