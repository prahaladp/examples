class Solution(object):
    def diagonalSort(self, mat):
        """
        :type mat: List[List[int]]
        :rtype: List[List[int]]
        """
        for i in range(len(mat[0]) - 1):
            j = 0
            i1 = i
            l = []
            while i1 < len(mat[0]) and j < len(mat):
                l.append(mat[j][i1])
                i1 = i1 + 1
                j = j + 1
            l.sort()
            i1 = i
            j = 0
            ind = 0
            for val in l:
                mat[j][i1] = val
                i1 = i1 + 1
                j = j + 1

        for i in range(1, len(mat) - 1):
            j = 0
            i1 = i
            l = []
            while i1 < len(mat) and j < len(mat[i1]):
                l.append(mat[i1][j])
                i1 = i1 + 1
                j = j + 1
            l.sort()
            i1 = i
            j = 0
            ind = 0
            for val in l:
                mat[i1][j] = val
                i1 = i1 + 1
                j = j + 1
        return mat
