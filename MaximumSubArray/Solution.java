
class Solution {
    public int maxSubArray(int[] nums) {
        int maxFar = Integer.MIN_VALUE;
        int sum = 0;
        
        for (int i: nums) {
            if (i > 0) {
                maxFar = i;
                break;
            }
            if (i > maxFar) {
                maxFar = i;
            }
        }
        
        if (maxFar < 0) {
            return maxFar;
        }
        maxFar = Integer.MIN_VALUE;
        for (int i : nums) {
            sum += i;
            if (sum < 0) {
                sum = 0;
            }
            if (maxFar < sum) {
                maxFar = sum;
            }
        }
        return maxFar;
    }
}
