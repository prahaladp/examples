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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<TreeNode> clt = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        
        if (root == null) {
            return res;
        }
        TreeNode ln = root;
        while (ln != null) {
            clt.add(0, ln);
            ln = ln.left;
        }
        while (clt.size() != 0) {
            TreeNode curr = clt.get(0);
            res.add(curr.val);
            clt.remove(0);
            ln = curr.right;
            while (ln != null) {
                clt.add(0, ln);
                ln = ln.left;
            }
        }
        return res;
    }
}
