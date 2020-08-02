class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int[] nb = new int[n];

        for (int i = 0; i < n; i++) {
            nb[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            if (nb[nums[i]] == nums[i]) {
                return nums[i];
            }
            nb[nums[i]] = nums[i];
        }
        return 0;
    }
}
