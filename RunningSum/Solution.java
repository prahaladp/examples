class Solution {
    public int[] runningSum(int[] nums) {
        int[] runSum = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            runSum[i] = sum;
        }
        return runSum;
    }
}
