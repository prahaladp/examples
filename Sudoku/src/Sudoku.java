import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Sudoku {
  public void solveSudoku(char[][] board) {
    Map<Integer, Set<Integer>> currR = new HashMap<>();
    Map<Integer, Set<Integer>> currC = new HashMap<>();

    if (board == null) {
      return;
    }
    
    for (int i = 0; i < board.length; i++) {
      Set<Integer> cr = new HashSet<>();
      for (int ij = 1; ij <= board.length; ij++) {
        cr.add(ij);
      }
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] != '.') {
          cr.remove(board[i][j] - '0');
        }
      }
      currR.put(i, cr);
    }
    
    for (int j = 0; j < board[0].length; j++) {
      Set<Integer> cc = new HashSet<>();
      for (int ij = 1; ij <= board.length; ij++) {
        cc.add(ij);
      }
      for (int i = 0; i < board.length; i++) {
        if (board[i][j] != '.') {
          cc.remove(board[i][j] - '0');
        }
      }
      currC.put(j, cc);
    }

    //System.out.println("Curr Row " + currR);
    //System.out.println("Curr Col " + currC);
    sudokoImpl(board, 0, 0, currR, currC);
    printBoard(board);
  }
  
  public int sudokoImpl(char [][] board, int x, int y,
      Map<Integer, Set<Integer>> currR,
      Map<Integer, Set<Integer>> currC) {
    
    int j = y;
    for (int i = x; i < board.length; i++) {
      for (; j < board[i].length; j++) {
        // System.out.println("[" + i + "," +j);
        if (board[i][j] == '.') {
          Set<Integer> cr = currR.get(i);
          Set<Integer> cc = currC.get(j);
          Set<Integer> tempR = new HashSet<>(cr);
          
          for (Integer val : tempR) {
            if (cc.contains(val) == true) {
              cr.remove(val);
              cc.remove(val);
              //System.out.println("[" + i + "," +j + "] val " + (char)('0' + val - 0));
              board[i][j] = (char)('0' + val - 0);
              
              int res;
              if (j == board[i].length - 1) {
                if (i == board.length - 1) {
                  return 1;
                }
                res = sudokoImpl(board,
                    i + 1, 0, currR, currC);
              } else {
                res = sudokoImpl(board,
                    i, j + 1, currR, currC);
              }
              if (res == 1) {
                return res;
              }
              
              cr.add(val);
              cc.add(val);
            }
          }
          //System.out.println("["+ i + "," + j +"] returning back");
          board[i][j] = '.';
          return -1;
        }
      }
      j = 0;
      if (i == board.length - 1) {
        return 1;
      }
    }
    return -1;
  }

  public void printBoard(char[][] board) {
    for (int i = 0; i < board.length; i++) {
      System.out.println(board[i]);
    }
  }
  
  public static void main(String[] arg) {
    char[][] board = new char[][] { 
      {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
      {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
      {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
    };
    Sudoku s = new Sudoku();
    s.printBoard(board);
    s.solveSudoku(board);
    
    board = new char[][] {
      {'.','.','9','7','4','8','.','.','.'},
      {'7','.','.','.','.','.','.','.','.'},
      {'.','2','.','1','.','9','.','.','.'},
      {'.','.','7','.','.','.','2','4','.'},
      {'.','6','4','.','1','.','5','9','.'},
      {'.','9','8','.','.','.','3','.','.'},
      {'.','.','.','8','.','3','.','2','.'},
      {'.','.','.','.','.','.','.','.','6'},
      {'.','.','.','2','7','5','9','.','.'}
    };
    s.printBoard(board);
    s.solveSudoku(board);
  }
}
