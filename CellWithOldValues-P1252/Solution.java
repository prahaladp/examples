class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        int[][] res = new int[m][n];
        int count = 0;
        
        for (int i = 0; i < indices.length; i++) {
            for (int j = 0; j < n; j++) {
                res[indices[i][0]][j] += 1;
            }
            for (int j = 0; j < m; j++) {
                res[j][indices[i][1]] += 1;
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (res[i][j] % 2 == 1) {
                    count = count + 1;
                }
            }
        }
        
        return count;
    }
}
