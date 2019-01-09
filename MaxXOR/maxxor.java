class Solution {
    public int findMaximumXOR(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        
        int max = -1;
        for (int i=1; i < nums.length; i++) {
            for (int j=0; j < i; j++) {
                int val = nums[i] ^ nums[j];
                if (val > max) {
                    max = val;
                }
            }
        }    
        return max;
    }
}
