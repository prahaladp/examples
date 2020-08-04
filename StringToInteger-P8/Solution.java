class Solution {
    public int myAtoi(String str) {
        String s = str.trim();

        if (s.length() == 0) {
            return 0;
        }

        Character c = s.charAt(0);
        if (s.charAt(0) != '+' && s.charAt(0) != '-' && Character.isDigit(c) != true) {
            return 0;
        }

        boolean minus = false;
        if (c == '-') {
            minus = true;
            s = s.substring(1);
        } else if (c == '+') {
            s = s.substring(1);
        }

        if (s.length() == 0 || Character.isDigit(s.charAt(0)) == false) {
            return 0;
        }

        int ind = 0;
        c = s.charAt(ind);
        while (ind < s.length() && s.charAt(ind) == '0') {
            ind++;
        }

        if (ind >= s.length()) {
            return 0;
        }

        if (Character.isDigit(s.charAt(ind)) == false) {
            return 0;
        }

        int num = 0;
        while (ind < s.length()) {
            c = s.charAt(ind);
            if (Character.isDigit(c) == false) {
                break;
            }

            long res = (long) num * (long) 10;
            if (res > Integer.MAX_VALUE) {
                if (minus == true) {
                    return -2147483648;
                }
                return Integer.MAX_VALUE;
            }

            num *= 10;
            res = (long) num + (long) Character.getNumericValue(c);
            if (res > Integer.MAX_VALUE) {
                if (minus == true) {
                    return -2147483648;
                }
                return Integer.MAX_VALUE;
            }
            num += Character.getNumericValue(c);
            // System.out.print(" str " + s + " num " + num + " \n");
            ind++;
        }

        if (minus == true) {
            num = 0 - num;
        }
        return num;
    }
}
