class Solution {
  private void markIsland(char[][] grid, int i, int j, char ch) {
      if (i >= grid.length || i < 0 || j < 0 || j >= grid[i].length ) {
          return;
      }
      if (grid[i][j] != '1') {
          return;
      }
      grid[i][j] = ch;
      markIsland(grid, i-1, j, ch);
      markIsland(grid, i+1, j, ch);
      markIsland(grid, i, j-1, ch);
      markIsland(grid, i, j+1, ch);
  }
  
  public int numIslands(char[][] grid) {
    int maxIs = 0;
    char ch = 'A';
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == '0') {
          continue;
        }
        if (grid[i][j] == '1') {
            markIsland(grid, i,j, ch);
            ch = (char)(ch + 1);
            maxIs++;
            continue;
        }        
      }
    }
    
    return maxIs;
  }
}
