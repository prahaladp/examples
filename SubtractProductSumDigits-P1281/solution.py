class Solution(object):
    def subtractProductAndSum(self, n):
        """
        :type n: int
        :rtype: int
        """
        s = str(n)
        p = 1
        a = 0
        for c in s:
            p = p * (ord(c)-ord('0'))
            a = a + (ord(c)-ord('0'))
        return p - a
