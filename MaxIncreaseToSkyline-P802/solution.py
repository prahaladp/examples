class Solution(object):
    def maxIncreaseKeepingSkyline(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        lr = [max(grid[i]) for i in range(len(grid))]
        tb = [i for i in grid[0]]
        for i in range(1, len(grid)):
            for j in range(0, len(grid[i])):
                if grid[i][j] > tb[j]:
                    tb[j] = grid[i][j]
             
        ms = 0
        for i in range(len(grid)):
            for j in range(len(grid[i])):
                s = min(lr[i], tb[j]) - grid[i][j]
                ms += s
                
        return ms
