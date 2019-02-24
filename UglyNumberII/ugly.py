import math

class Solution(object):
    def nthUglyNumber(self, n):
        """
        :type n: int
        :rtype: int
        """
        num = 1
        while n > 0:
            if self.isUgly(num) == True:
                nth = num
                n -= 1
            num += 1
        return nth
        
    def isUgly(self, n):
        f=[]
        if n in [1,2,3,5]:
            return True
        o = n
        while n%2 == 0:
            f = [2]
            n/=2
        i = 3
        while i <= math.sqrt(n):
            nf = []
            while n%i == 0:
                nf = [i]
                n/=i;
            f += nf
            i += 2
        if len(f) == 0:
            return False
        print o, f
        for factor in f:
            if factor not in [2,3,5]:
                return False
        return True

s = Solution()
for i in range(1,10):
   print i,  s.nthUglyNumber(i)
