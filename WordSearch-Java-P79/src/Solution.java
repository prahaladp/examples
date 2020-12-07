
class Solution {
  
    public boolean completeSearch(char[][] board, int x, int y, String word, int indx) {
      int[][] nextInd = {
          {1, 0},
          {1, 1},
          {0, 1},
          {-1, 1},
          {-1, 0},
          {-1, -1},
          {0, -1},
          {1, -1}
      };
      
      if (indx >= word.length()) {
        return true;
      }
      
      for (int i = 0; i < nextInd.length; i++) {
        int nx = x + nextInd[i][0];
        int ny = y + nextInd[i][1];
        
        if (nx >= board.length) {
          nx = 0;
        }
        if (nx < 0) {
          nx = board.length - 1;
        }
        if (ny >= board[x].length) {
          ny = 0;
        }
        if (ny < 0) {
          ny = board[x].length - 1;
        }
        if (word.charAt(indx) == board[nx][ny]) {
          board[nx][ny] = '?';
          if (completeSearch(board, nx, ny, word, indx + 1) == true) {
            return true;
          }
          board[nx][ny] = word.charAt(indx);
        }
      }
      return false;
    }
    
    public boolean exist(char[][] board, String word) {
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
          if (board[i][j] == word.charAt(0)) {
            board[i][j] = '?';
            if (completeSearch(board, i, j, word, 1) == true) {
              return true;
            }
            board[i][j] = word.charAt(0);
          }
        }
      }
      return false;
    }
    
    public static void main(String[] argc) {
      char[][ ]board =
        {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
      Solution s = new Solution();
      
      String[] test = { "ABCCED", "SEE", "ABCB"};
      for (String t : test) {
        System.out.println(t + "  :  " + s.exist(board, t));
      }
    }
}