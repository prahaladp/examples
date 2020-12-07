class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {
        int m = a;
        
        if (b<m) {
            m = b;
        }
        if (c<m) {
            m = c;
        }
        
        n--;
        for (int i = m+1; ; i++) {
            if (i%a == 0 || i%b == 0 || i%c == 0) {
                if (n==1) {
                    return i;
                }
                n--;
            }
        }
    }
}
