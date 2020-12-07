class Solution {
    public int subarraySum(int[] nums, int k) {
        int tc =0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) {
                tc++;
            }
            for (int j = i + 1, s= nums[i]; j < nums.length;j++) {
                s+=nums[j];
                if (s == k) {
                    tc++;
                }
            }
        }
        return tc;
        
    }
}
