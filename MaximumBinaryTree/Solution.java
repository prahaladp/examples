class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
           return partTree(nums, 0, nums.length - 1);
    }
    
    private TreeNode partTree(int[] nums, int s, int e) {
        if (s > e) {
            return null;
        }
        int mx = s;
        int os = s;
        s++;
        while (s <= e) {
            if (nums[s] > nums[mx]) {
                mx = s;
            }
            s++;
        }
        TreeNode nt = new TreeNode(nums[mx]);
        nt.left = partTree(nums, os, mx - 1);
        nt.right = partTree(nums, mx + 1, e);
        return nt;
    }
}
