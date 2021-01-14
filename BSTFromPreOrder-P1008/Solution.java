class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        for (int i=1; i < preorder.length; i++) {
            TreeNode r = root;
            while (true) {
                if (preorder[i] > r.val) {
                    if (r.right == null) {
                        TreeNode n = new TreeNode(preorder[i]);
                        r.right = n;
                        break;
                    }
                    r = r.right;
                    continue;
                }
                if (preorder[i] < r.val) {
                    if (r.left == null) {
                        TreeNode n = new TreeNode(preorder[i]);
                        r.left = n;
                        break;
                    }
                    r = r.left;
                    continue;
                }
            }
        }
        return root;
    }
}
