class Solution {
    public boolean isPowerOfThree(int n) {
        if (n == 1) {
            return true;
        }
        if (n < 3) {
            return false;
        }
        int rem = n % 3;
        
        while (n >= 3 && rem == 0) {
            rem = n % 3;
            n /= 3;
        }
        
        return (n == 1 && rem == 0) ? true : false;
    }
}
