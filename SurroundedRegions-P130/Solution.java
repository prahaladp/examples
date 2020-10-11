class Solution {
  private void markIsland(char[][] grid, int i, int j, char check, char ch) {
      if (i >= grid.length || i < 0 || j < 0 || j >= grid[i].length ) {
          return;
      }
      if (grid[i][j] != check) {
          return;
      }
      grid[i][j] = ch;
      markIsland(grid, i-1, j, check, ch);
      markIsland(grid, i+1, j, check, ch);
      markIsland(grid, i, j-1, check, ch);
      markIsland(grid, i, j+1, check, ch);
  }
    public void solve(char[][] grid) {
        int x = 0, y = 0;
        if (grid.length == 0) {
            return;
        }
        for (x = 0; x < grid.length; x++) {
            if (grid[x][y] == 'O') {
                markIsland(grid, x, y, 'O', 'Y');
            }
        }
        y=grid[0].length-1;
        for (x = 0; x < grid.length; x++) {
            if (grid[x][y] == 'O') {
                markIsland(grid, x, y, 'O', 'Y');
            }
        }   
        x=0;
        for (y = 0; y < grid[x].length; y++) {
            if (grid[x][y] == 'O') {
                markIsland(grid, x, y, 'O', 'Y');
            }
        }   
        x=grid.length - 1;
        for (y = 0; y < grid[x].length; y++) {
            if (grid[x][y] == 'O') {
                markIsland(grid, x, y, 'O', 'Y');
            }
        }   
        for (x = 0; x < grid.length; x++) {
            for (y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == 'O') {
                    markIsland(grid, x, y, 'O', 'X');
                }
            }
        }
        for (x = 0; x < grid.length; x++) {
            for (y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == 'Y') {
                    markIsland(grid, x, y, 'Y', 'O');
                }
            }
        }       
    }
}
