class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length <= 1) {
            return false;
        }
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i + 1, s= nums[i]; j < nums.length;j++) {
                s+=nums[j];
                if (k == 0 && s==0) {
                    return true;
                }
                if (k!=0 && s%k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
