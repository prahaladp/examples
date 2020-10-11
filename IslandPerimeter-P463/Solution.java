class Solution {
    int perimeter = 0;
    private int markNeighbours(int[][] grid, int x, int y, int mx, int my, int val) {
        if (x >= mx || y >= my || x < 0 || y < 0) {
            return 1;
        }
        if (grid[x][y] == val) {
            return 0;
        }
        if (grid[x][y] == 0) {
            return 1;
        }
        grid[x][y] = val;
        //System.out.println(x + "," + y + " : " + this.perimeter);
        int p = markNeighbours(grid, x-1, y, mx, my, val) +
            markNeighbours(grid, x+1, y, mx, my, val) +
            markNeighbours(grid, x, y-1, mx, my, val) +
            markNeighbours(grid, x, y+1, mx, my, val);
        //System.out.println(x + "," + y + " : " + this.perimeter);
        this.perimeter += p;
        return 0;
    }

    public int islandPerimeter(int[][] grid) {
        int mx = grid.length;
        int my = grid[0].length;
        int x=0;
        int y;
        this.perimeter = 0;
        for (x=0;x<mx; x++) {
            for (y=0; y<my; y++) {
                if (grid[x][y] == 1) {
                    markNeighbours(grid, x, y, mx, my, -1);
                    break;
                }
            }
        }
        return this.perimeter;
    }
}
