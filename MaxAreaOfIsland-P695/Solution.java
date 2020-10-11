class Solution {
    private int markNeighbours(int[][] grid, int x, int y, int mx, int my, int val) {
        if (x >= mx || y >= my || x < 0 || y < 0) {
            return 0;
        }
        if (grid[x][y] != 1) {
            return 0;
        }
        grid[x][y] = val;
        return 1 + markNeighbours(grid, x-1, y, mx, my, val) +
        markNeighbours(grid, x+1, y, mx, my, val) +
        markNeighbours(grid, x, y-1, mx, my, val) +
        markNeighbours(grid, x, y+1, mx, my, val);
    }

    public int maxAreaOfIsland(int[][] grid) {
        int mx = grid.length;
        int my = grid[0].length;
        int x=0;
        int y;
        for (y=0; y<my; y++) {
            if (grid[x][y] == 1) {
                continue;
            }
            markNeighbours(grid, x, y, mx, my, -1);     
        }
        x=mx-1;
        for (y=0; y<my; y++) {
            if (grid[x][y] == 1) {
                continue;
            }
            markNeighbours(grid, x, y, mx, my, -1);     
        }
        y=0;
        for (x=0; x<mx; x++) {
            if (grid[x][y] == 1) {
                continue;
            }
            markNeighbours(grid, x, y, mx, my, -1);     
        }
        y=my-1;
        for (x=0; x<mx; x++) {
            if (grid[x][y] == 1) {
                continue;
            }
            markNeighbours(grid, x, y, mx, my, -1);     
        }
        int ma = Integer.MIN_VALUE;
        for (x=0;x<mx; x++) {
            for (y=0; y<my; y++) {
                if (grid[x][y] == 1) {
                    int ar = markNeighbours(grid, x, y, mx, my, -1);
                    if (ar > ma) {
                        ma = ar;
                    }
                }
            }
        }
        if (ma == Integer.MIN_VALUE) {
            ma = 0;
        }
        return ma;
    }
}
