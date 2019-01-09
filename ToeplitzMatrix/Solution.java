class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int row = 0;
        int col = 0;
        for (col=0; col < matrix[row].length; col++) {
            int val = matrix[row][col];
            for (int i=row+1, j=col+1; i < matrix.length && j < matrix[row].length;
                i++, j++) {
                if (matrix[i][j] != val) {
                    return false;
                }
            }
        }
        
        col = 0;
        for (row=1; row < matrix.length; row++) {
            int val = matrix[row][col];
            for (int i=row+1, j=col+1; i < matrix.length && j < matrix[row].length;
                i++, j++) {
                if (matrix[i][j] != val) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String args[]) {
        int[][] matrix = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        Solution s = new Solution();
        System.out.println(s.isToeplitzMatrix(matrix));
    }
}
