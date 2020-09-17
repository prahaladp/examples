class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        boolean up = true;
        int ind = 0;
        int x = 0;
        int y = 0;
        int xlen = matrix.length;
        if (xlen == 0) {
            return new int[0];
        }
        int ylen = matrix[0].length;
        int te = xlen * ylen;
        int[] res = new int[te];
        
        while (ind < te) {
            //System.out.println(x + " " + y + " " + ind);
            res[ind++] = matrix[x][y];
            if (up == true) {
                if (x == 0 && y == ylen - 1) {
                    x = x + 1;
                    up = false;
                    continue;
                }
                if (x == 0) {
                    x = 0; y = y + 1;
                    up = false;
                    continue;
                }
                if (y == ylen -1) {
                    x = x + 1;
                    up = false;
                    continue;
                }
                x = x - 1;
                y = y + 1;
            } else {
                if (y == 0 && x == xlen - 1) {
                    y = y + 1;
                    up = true;
                    continue;
                }
                if (x == xlen - 1) {
                    y = y + 1;
                    up = true;
                    continue;
                }
                if (y == 0) {
                    x = x + 1;
                    up = true;
                    continue;
                }
                x = x + 1;
                y = y - 1;
            }
        }
        return res;
    }
}
