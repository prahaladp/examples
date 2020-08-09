class Solution(object):
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        for i in range(0, len(matrix)):
            if len(matrix[i]) == 0:
                continue
            if target < matrix[i][0]:
                return False
            if i != len(matrix) - 1:
                if target < matrix[i+1][0]:
                    for j in range(0, len(matrix[i])):
                        if target == matrix[i][j]:
                            return True
                    return False
            else:
                for j in range(0, len(matrix[i])):
                    if target == matrix[i][j]:
                        return True
                return False
        return False
        
