class Solution {
    public String intToRoman(int num) {
        StringBuilder str 
            = new StringBuilder();   
        
        int n = genThousand(num, str);
        n = genHundred(n, str);
        n = genTens(n, str);
        n = genSingle(n, str);
        return str.toString();
    }
    
    private int genThousand(int n, StringBuilder str) {
        if (n >= 1000) {
            getRoman(str, n/1000, 'M');
        }
        return n % 1000;
    }
    
    private int genHundred(int n, StringBuilder str) {
        if (n < 100) {
            return n;
        }
        if (n >= 900) {
            str.append("CM");
            return n % 100;
        }
        if (n >= 500) {
            str.append('D');
            genHundred(n-500, str);
            return n % 100;
        }
        if (n >= 400) {
            str.append("CD");
            return n % 100;
        }
        getRoman(str, n/100, 'C');
        return n % 100;
    }
    
    private int genTens(int n, StringBuilder str) {
        if (n < 10) {
            return n;
        }
        if (n >= 90) {
            str.append("XC");
            return n % 10;
        }
        if (n >= 50) {
            str.append('L');
            genTens(n-50, str);
            return n % 10;
        }
        if (n >= 40) {
            str.append("XL");
            return n % 10;
        }
        getRoman(str, n/10, 'X');
        return n % 10;
    }
    
    private int genSingle(int n, StringBuilder str) {
        if (n > 10) {
            return 0;
        }
        if (n >= 9) {
            str.append("IX");
            return 0;
        }
        if (n >= 5) {
            str.append('V');
            genSingle(n-5, str);
            return 0;
        }
        if (n >= 4) {
            str.append("IV");
            return 0;
        }
        getRoman(str, n, 'I');
        return 0;
    }

    
    
    private void getRoman(StringBuilder str, int n, char r) {
        while (n > 0) {
            str.append(r);
            n--;
        }
    }
    
}
