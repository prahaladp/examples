class Solution(object):
    path_map = {}
    
    def compute_min_path(self, triangle, mp, row, cols):
        if row >= len(triangle):
            return mp
        if row not in self.path_map:
            self.path_map[row] = {}
        if cols[0] in self.path_map[row]:
            mp1 = mp + self.path_map[row][cols[0]]
        else:
            mp1 = self.compute_min_path(triangle, triangle[row][cols[0]], row + 1, [cols[0], cols[0] + 1])
            self.path_map[row][cols[0]] = mp1
            mp1 = self.path_map[row][cols[0]] + mp
        if cols[1] in self.path_map[row]:
            mp2 = mp + self.path_map[row][cols[1]]
        else:
            mp2 = self.compute_min_path(triangle, triangle[row][cols[1]], row + 1, [cols[1], cols[1] + 1])
            self.path_map[row][cols[1]] = mp2
            mp2 = self.path_map[row][cols[1]] + mp

        if mp1 > mp2:
            return mp2
        return mp1
        
    def minimumTotal(self, triangle):
        """
        :type triangle: List[List[int]]
        :rtype: int
        """
        self.path_map = {}
        return self.compute_min_path(triangle, triangle[0][0], 1, [0,1])
