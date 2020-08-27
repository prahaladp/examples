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
    private boolean validate(List<Integer> mmList, TreeNode tn) {
        if (tn == null) {
            return true;
        }
        List<Integer> leftN = new LinkedList<>();
        List<Integer> rightN = new LinkedList<>();
        if (validate(leftN, tn.left) == false || validate(rightN, tn.right) == false) {
            return false;
        }
        if (leftN.size() > 0 && (leftN.get(0) >= tn.val || leftN.get(1) >= tn.val)) {
            return false;
        }
        if (rightN.size() > 0 && (rightN.get(0) <= tn.val || rightN.get(1) <= tn.val)) {
            return false;
        }
        leftN.add(tn.val);
        rightN.add(tn.val);
        Integer minVal = leftN.stream().min(Integer::compare).get();
        Integer maxVal = rightN.stream().max(Integer::compare).get();
        mmList.add(minVal);
        mmList.add(maxVal);
        return true;
    }
        
    public boolean isValidBST(TreeNode root) {
        List<Integer> mmList = new LinkedList<>();
        return validate(mmList, root);
    }
}
