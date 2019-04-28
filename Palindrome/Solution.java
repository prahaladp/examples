class Solution {
    public boolean isPalindrome(int x) {
        int r = 0;
        int orig = x;
        
        if (x < 0) {
            x = (-x);
            orig = x;
            return false;
        }
        
        while (x != 0) {
            r = (r * 10) + (x%10);
            x /= 10;
        }
        
        if (r == orig) {
            return true;
        }
        
        return false;
    }
}
