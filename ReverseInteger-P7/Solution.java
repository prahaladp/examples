class Solution {
    public int reverse(int x) {        
        boolean minus = false;
        if (x < 0) {
            minus = true;
            long res = (long) x * (long) -1;
            if (res > Integer.MAX_VALUE) {
                return 0;
            }
            x *= -1;
        }
        
        int div = 10;
        int ans = 0;
        int mul = 10;
        while (x != 0) {
            int nn = x % div;
            long res = (long) ans * (long) mul;
            if (res > Integer.MAX_VALUE) {
                return 0;
            }
            res = res + (long) nn;
            if (res > Integer.MAX_VALUE) {
                return 0;
            }
            ans = (ans * mul) + nn;
            x = x / div;
        }   
        
        if (minus == true) {
            return ans * -1;
        }
        return ans;
    }
}
