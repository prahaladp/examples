/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int findDepth(TreeNode r) {
        if (r == null) {
            return 0;
        }
        if (r.left == null && r.right == null) {
            return 1;
        }
        int ld = 0;
        int rd = 0;
        if (r.left != null) {
            ld = 1 + findDepth(r.left);
        }
        if (r.right != null) {
            rd = 1 + findDepth(r.right);
        }
        return ld > rd ? ld : rd;
    }
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        TreeNode nn = root;
        
        while (nn != null) {
            int ld = findDepth(nn.left);
            int rd = findDepth(nn.right);
            if (ld == rd) {
                return nn;
            }
            if (ld > rd) {
                nn = nn.left;
            } else {
                nn = nn.right;
            }
        }
        return null;
    }
}
