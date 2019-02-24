class Solution {
    public int reverse(int x1) {
        boolean neg = false;
        if (x < 0) {
            neg = true;
            x *= -1;
        }
        
        long num = 0;
        int m = 10;
        int n = 10;
        Long x = Long(x1);
        while (x != 0) {
            int r = x % m;
            x /= m;
            num *= n;
            num += r;
        }
        
        int ans = toIntExact(num);
        if (neg) {
            ans *= -1;
        }

        return ans;
    }

}
