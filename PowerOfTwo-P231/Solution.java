class Solution {
    public boolean isPowerOfTwo(int n) {
        int num = 1;
        while (num < n) {
            num = num * 2;
        }
        return (num == n);
    }
}
