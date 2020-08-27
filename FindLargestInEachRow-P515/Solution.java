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
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        
        int maxNum = Integer.MIN_VALUE;
        List<Integer> maxNumList = new LinkedList<>();
        List<TreeNode> cQ = new LinkedList<>();
        cQ.add(root);
        
        while (cQ.size() != 0) {
            int mi = cQ.stream().map(e->e.val).max(Integer::compare).get();
            maxNumList.add(mi);
            List<TreeNode> nQ = new LinkedList<>();
            cQ.stream().forEach(e -> {
                if (e.right != null) {
                    nQ.add(e.right);
                }
                if (e.left != null) {
                    nQ.add(e.left);
                }
            });
            cQ = nQ;
            
        }
        return maxNumList;
    }
}
