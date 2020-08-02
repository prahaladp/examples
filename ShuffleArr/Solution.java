class Solution {
    public int[] shuffle(int[] nums, int n) {
        if (n * 2 > nums.length) {
            return null;
        }
        
        int[] shuffleArr = new int[n * 2];
        for (int i = 0, j = i + n, k = 0; i < n; i++, j++) {
            shuffleArr[k++] = nums[i];
            shuffleArr[k++] = nums[j];
        }
        
        return shuffleArr;
    }
}
