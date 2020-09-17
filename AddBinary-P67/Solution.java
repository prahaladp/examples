class Solution {
    public String addBinary(String a, String b) {
        int mal = (a.length() > b.length()) ? a.length() : b.length();
        int mil = (a.length() > b.length()) ? b.length() : a.length();
        String bigs = (a.length() > b.length()) ? a : b;
        String smalls = (a.length() > b.length()) ? b : a;

        char[] res = new char[mal+1];
        res[0] = ' ';
        int carryover = 0;
        int ins = mal;
        int i = mil - 1;
        int j = mal - 1;
        for (; i >= 0; i--, j--) {
            if (bigs.charAt(j) == '1' && smalls.charAt(i) == '1') {
                if (carryover == 0) {
                    res[ins--] = '0';
                } else {
                    res[ins--] =  '1';
                }
                carryover = 1;
            } else if ((bigs.charAt(j) == '0' && smalls.charAt(i) == '1') ||
                (bigs.charAt(j) == '1' && smalls.charAt(i) == '0')) {
                if (carryover == 0) {
                    res[ins--] = '1';
                } else {
                    res[ins--] = '0';
                    carryover = 1;
                }
            } else if (bigs.charAt(j)== '0' && smalls.charAt(i) == '0') {
                if (carryover == 0) {
                    res[ins--] = '0';
                } else {
                    res[ins--] = '1';
                    carryover = 0;
                }
            }
            // System.out.println(res[ins+1]);
        }
        for (; j >= 0; j--) {
            if (bigs.charAt(j) == '1') {
                if (carryover == 0) {
                    res[ins--] = '1';
                } else {
                    res[ins--] = '0';
                    carryover = 1;
                }
            } else if (bigs.charAt(j) == '0') {
                if (carryover == 0) {
                    res[ins--] = '0';
                } else {
                    res[ins--] = '1';
                    carryover = 0;
                }
            }
            // System.out.println(res[ins+1]);
        }
        if (carryover == 1) {
            res[ins--] = '1';
        }
        return (String.valueOf(res).trim());
    }
}
