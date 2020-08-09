class Solution {
    public int divide(int dividend, int divisor) {
        Long nd = Long.valueOf(dividend);
        Long di = Long.valueOf(divisor);
        Long ans = 0L;
        boolean minus = false;

        if (dividend < 0 && divisor < 0) {
            nd *= -1;
            di *= -1;
        } else if (dividend < 0) {
            minus = true;
            nd *= -1;
        } else if (divisor < 0) {
            minus = true;
            di *= -1;
        }
        while (nd >= di) {
            Long mul = 0L;
            Long cd = Long.valueOf(di);

            while (cd <= nd) {
                if (cd.compareTo(di) == 0) {
                    mul = 1L;
                } else {
                    mul = mul * 2;
                }
                cd <<= 1;
            }
            ans += mul;
            nd -= (cd >> 1);
        }

        ans = (minus == true) ? ans *= -1: ans;

        if (ans > Integer.MAX_VALUE) {
            return (Integer.MAX_VALUE);
        } else if (ans < Integer.MIN_VALUE) {
            return (Integer.MIN_VALUE);
        }
        return ans.intValue();
    }
}
