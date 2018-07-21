class Solution(object):
    def maxRotateFunction(self, A):
        n = len(A)
        if n == 0:
            return 0
        for i in range(n):
            s = self.rotate(A, i)
            if i == 0:
                maxF = s
            elif s > maxF:
                maxF = s
        return maxF

    def rotate(self, A, i):
        n = len(A)
        s = 0
        for ind in range(n):
            s = s + (A[i] * ind)
            i = i + 1
            if i >= n:
                i = 0

        return s


s = Solution()
A = [4, 3, 2, 6]
print s.maxRotateFunction(A)
