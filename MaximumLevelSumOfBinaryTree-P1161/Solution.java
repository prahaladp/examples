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
    public int maxLevelSum(TreeNode root) {
        List<TreeNode> cq = new LinkedList<>();
        cq.add(root);
        int maxL = Integer.MIN_VALUE;
        int level = 1;
        int ans = -1;
        while (cq.isEmpty() == false) {
            int sum = cq.stream().mapToInt(n->n.val).sum();
            if (sum > maxL) {
                maxL = sum;
                ans = level;
            }
            List<TreeNode> nq = new LinkedList<>();
            cq.stream().forEach(n -> {
                if (n.left != null) {
                    nq.add(n.left);
                } 
                if (n.right != null) {
                    nq.add(n.right);
                }
            });
            level = level + 1;
            cq = nq;
        }
        return ans;
    }
}
