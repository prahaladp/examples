class Solution(object):
    def minSteps(self, n):
        """
        :type n: int
        :rtype: int
        """
        a=0
        while n%2 == 0:
            n=n/2
            a=a+2
        if n == 1:
            return a
        for i in range(3,n+1):
            while n%i == 0:
                n=n/i
                a=a+i
            if n == 1:
                return a
        return a
