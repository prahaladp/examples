
public class Solution {
  
  private boolean isaKnownIsland(char[][] grid, int i, int j) {
    if (i > 0 && grid[i-1][j] == 'X') {
      return true;
    }
    if (j > 0 && grid[i][j-1] == 'X') {
      return true;
    }
    if (i < grid.length - 1 && grid[i+1][j] == 'X') {
      return true;
    }
    if (j < grid[i].length - 1 && grid[i][j+1] == 'X') {
      return true;
    }
    
    /* check diagonally, but verify that the adjacent is 1 as well */
    if (i > 0 && j < grid[i].length-1) {
      if (grid[i-1][j+1] == 'X' && grid[i][j+1] == '1') {
        return true;
      }
    }
    
    return false;
  }
  
  public int numIslands(char[][] grid) {
    int maxIs = 0;
    
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == '0') {
          continue;
        }
        
        if (grid[i][j] == 'X') {
          /* already discovered island */
          continue;
        }
        
        if (isaKnownIsland(grid, i, j) == true) {
          grid[i][j] = 'X';
          continue;
        }
        
        maxIs++;
        grid[i][j] = 'X';
      }
    }
    
    return maxIs;
  }
  
  public static void main(String[] args) {
    char[][] grid = {
        {'1','1','1','1','0'},
        {'1','1','0','1','0'},
        {'1','1','0','0','0'},
        {'0','0','0','0','0'}
    };
    
    Solution s = new Solution();
    System.out.println("maxIsland = " + s.numIslands(grid));
  }
}
