
public class Solution {
  
  public boolean findSquare(char[][] matrix, int x, int y, int mx, int my, int k) {
    int i = x + k - 1;
    int j = y;
    
    System.out.println("findSquare " + x + " " + y + " " + mx + " " + my + " " + k);
    if (i >= mx) {
      return false;
    }
    
    for (;j < y + k && matrix[i][j] == '1'; j++);
    if (j != y + k) {
      return false;
    }
    
    for (; i >= x && matrix[i][j-1] == '1'; i--);
    if (i >= x) {
      return false;
    }
    
    return true;
  }
  
  public int maximalSquare(char[][] matrix) {
    int maxSq = 0;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        int maxPossible = Math.min(matrix.length - i,
            matrix[i].length - j);
        if (maxPossible == 1) {
          if (matrix[i][j] == '1') {
            maxSq = Math.max(maxSq, 1);
          }
          continue;
        }
        int k = 1;
        for (; k <= maxPossible; k++) {
          if (findSquare(matrix, i, j, matrix.length, matrix[i].length, k) == false) {
            break;
          }
        }      
        maxSq = Math.max(maxSq, (k - 1) * (k - 1));
      }
    }
    
    return maxSq;
  }
  
  public static void main(String args[]) {
    char[][] matrix = {
        {'1', '0', '1', '0', '0'},
        {'1', '0', '1', '1', '1'},
        {'1', '1', '1', '1', '1'},
        {'1', '0', '0', '1', '0'}
    };
    
    Solution s = new Solution();

    char[][] matrix1 = {{'1'}};
    System.out.println("Max Sq = " + s.maximalSquare(matrix1));

    System.out.println("Max Sq = " + s.maximalSquare(matrix));
    
    char[][] matrix2 = {{'1', '1'}, {'1', '1'}};
    System.out.println("Max Sq = " + s.maximalSquare(matrix2));


  }
}
