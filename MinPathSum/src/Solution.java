
public class Solution {
  public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    /*
     * m,n is the end point we start with <(x-1), n> .... <(x-1,y)> <x, y-1>, <m, y-1>, <m-1, y-1>,
     * <x, y-1>, <x-1,y-1>
     */
    
    int x = m;
    int y = n;
    
    while (x-1 >=0 && y-1 >=0) {
      int nx = x-1;
      int ny = n;
      while (ny > y-1) {
        int sm = grid[nx+1][ny];
        if (ny + 1 < n && grid[nx][ny+1] < sm) {
          sm = grid[nx][ny+1];
        }
        grid[nx][ny] = sm;
      }
      
      nx = y-1;
      ny = 
    }
  }
}
