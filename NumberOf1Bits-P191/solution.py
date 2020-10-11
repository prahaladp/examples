class Solution(object):
    def hammingWeight(self, n):
        """
        :type n: int
        :rtype: int
        """
        c=0
        for i in bin(n):
            if i == '1':
                c=c+1
        return c
