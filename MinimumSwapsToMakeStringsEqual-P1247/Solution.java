class Solution {
    public int minimumSwap(String s1, String s2) {
        int xy = 0; 
        int yx =0;
        for (int i=0; i < s1.length(); i++) {
            if (s1.charAt(i) == 'x' && s2.charAt(i) == 'y') {
                xy++;
            } else if (s1.charAt(i) == 'y' && s2.charAt(i) == 'x') {
                yx++;
            }
        }
        
        if (xy % 2 == 0 && yx % 2 == 0) {
            return (xy + yx)/2;
        }
        if (xy % 2 == 1 && yx % 2 == 1) {
            return xy/2 + yx/2 + 2;
        }
        return -1;
    }
}
