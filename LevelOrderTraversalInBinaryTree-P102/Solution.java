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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> retList = new LinkedList<>();
        List<TreeNode> currList = new LinkedList<>();
        List<TreeNode> nextList = new LinkedList<>();
        
        if (root == null) {
            return retList;
        }
        
        nextList.add(root);
        while (nextList.size() != 0) {
            List<Integer> levelCopy = nextList.stream().map(e -> e.val).collect(Collectors.toList());
            retList.add(levelCopy);
            
            currList = new LinkedList<>();
            currList.addAll(nextList);
            nextList.clear();
            currList.stream().forEach(t -> {
                if (t.left != null) {
                    nextList.add(t.left);
                }
                if (t.right != null) {
                    nextList.add(t.right);
                }
            });
        }
        return retList;
    }
}
