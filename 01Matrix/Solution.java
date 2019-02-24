class Solution {
    private void updateNearby(int[][] matrix, int i, int j) {
        if (i > 0 && matrix[i-1][j] > matrix[i][j]) {
            matrix[i-1][j] =  matrix[i][j] + 1;
        } 
        
        if (i < matrix.length-1 && matrix[i+1][j] > matrix[i][j]) {
            matrix[i+1][j] = matrix[i][j] + 1;
        }
        if (j > 0 && matrix[i][j-1] > matrix[i][j]) {
            matrix[i][j-1] = matrix[i][j] + 1;
        }
        if (j < matrix[i].length-1 && matrix[i][j+1] > matrix[i][j]) {
            matrix[i][j+1] = matrix[i][j] + 1;
        }
    }
    
    public int[][] updateMatrix(int[][] matrix) {
        int ucount = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }

                if ((i > 0 && matrix[i-1][j] == 0) ||
                    (i < matrix.length-1 && matrix[i+1][j] == 0) ||
                    (j > 0 && matrix[i][j-1] == 0) || 
                    (j < matrix[i].length-1 && matrix[i][j+1] == 0)) {
                        matrix[i][j] = 1;
                    } else {
                        matrix[i][j] = -1;
                        ucount++;
                    }
            }
        }
        
        if (ucount == 0) {
            return matrix;
        }
        
        while (ucount != 0) {
            ucount = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] != -1) {
                        continue;
                    }
                    if (i > 0 && matrix[i-1][j] >= 0 && matrix[i][j] > matrix[i-1][j] + 1 ) {
                        matrix[i][j] = matrix[i-1][j] + 1;
                        updateNearby(matrix, i, j);
                    } else if (i < matrix.length-1 && matrix[i+1][j] >= 0 && matrix[i][j] > matrix[i+1][j] + 1) {
                        matrix[i][j] = matrix[i+1][j] + 1;
                        updateNearby(matrix, i, j);
                    } else if (j > 0 && matrix[i][j-1] >= 0 && matrix[i][j] > matrix[i][j-1] + 1) {
                        matrix[i][j] = matrix[i][j-1] + 1;
                        updateNearby(matrix, i, j);
                    } else if (j < matrix[i].length-1 && matrix[i][j+1] >= 0 && matrix[i][j] > matrix[i][j+1] + 1) {
                        matrix[i][j] = matrix[i][j+1] + 1;
                        updateNearby(matrix, i, j);
                    } else {
                        matrix[i][j] = -1;
                        ucount++;
                    }
                }
            }
        }
        
        return matrix;
    }
}
