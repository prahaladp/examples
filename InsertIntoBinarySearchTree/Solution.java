class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        
        TreeNode prev = root;
        while (prev != null) {
            TreeNode next = val > prev.val ? prev.right : prev.left;
            if (next == null) {
                break; 
            }
            prev = next;
        }
        if (val > prev.val) {
            prev.right = new TreeNode(val);
        } else {
            prev.left = new TreeNode(val);
        }
        return root;
    }
}
