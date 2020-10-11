class Solution(object):
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        if len(matrix) == 0:
            return False
        if len(matrix[0]) == 0:
            return False
        if target < matrix[0][0]:
            return False
        maxx = -1
        for i in range(len(matrix)-1, -1, -1):
            if matrix[i][0] == target:
                return True
            if matrix[i][0] > target:
                continue
            maxx = i
            break
        if maxx == -1:
            return False
        maxy = -1
        for i in range(len(matrix[0])-1, -1, -1):
            if matrix[0][i] == target:
                return True
            if matrix[0][i] > target:
                continue
            maxy = i
            break
        if maxy == -1:
            return False
        for x in range(0, maxx+1):
            for y in range(0, maxy+1):
                if matrix[x][y] == target:
                    return True
        return False
    
