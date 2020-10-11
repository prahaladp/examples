class Solution {
    private void markNeighbours(int[][] grid, int x, int y, int mx, int my, int cc, int val) {
        if (x >= mx || y >= my || x < 0 || y < 0) {
            return;
        }
        if (grid[x][y] != cc) {
            return;
        }
        grid[x][y] = val;
        markNeighbours(grid, x-1, y, mx, my, cc, val);
        markNeighbours(grid, x+1, y, mx, my, cc, val);
        markNeighbours(grid, x, y-1, mx, my, cc, val);
        markNeighbours(grid, x, y+1, mx, my, cc, val);
    }

    public int[][] floodFill(int[][] grid, int sr, int sc, int newColor) {
        if (grid.length == 0) {
            return grid;
        }
        if (grid[0].length == 0) {
            return grid;
        }
        int mx = grid.length;
        int my = grid[0].length;
        int cc= grid[sr][sc];
        if (cc == newColor) {
            return grid;
        }
        markNeighbours(grid, sr, sc, mx, my, cc, newColor);
        return grid;
    }
}
