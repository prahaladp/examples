class Solution {
    public int xorOperation(int n, int start) {
        int[] nums = new int[n];
        if (n <= 0) {
            return 0;
        }
        for (int i = 0; i < n; i++) {
            nums[i] = start + (i * 2);
        }
        int xor = nums[0];
        for (int i = 1; i < n; i++) {
            xor ^= nums[i];
        }
        return xor;
    }
}
