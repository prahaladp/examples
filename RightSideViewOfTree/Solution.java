/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<TreeNode> q = new LinkedList<>();
        List<Integer> iq = new LinkedList<>();
        if (root == null) {
            return iq;
        }
        q.add(root);
        
        while (q.size() != 0) {
            List<TreeNode> newq = new LinkedList<>();
            for (TreeNode t : q) {
                if (t.left != null) {
                    newq.add(t.left);
                }
                if (t.right != null) {
                    newq.add(t.right);
                }
            }
            iq.add(q.get(q.size() - 1).val);
            q = newq;
        }
        return iq;
    }
}
