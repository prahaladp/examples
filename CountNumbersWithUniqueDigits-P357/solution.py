class Solution(object):
    def countNumbersWithUniqueDigits(self, n):
        """
        :type n: int
        :rtype: int
        """
        d=10
        c=0
        num=0
        while num < pow(10, n):
            m={}
            n1 = num
            u=True
            while n1 != 0:
                r=n1%d
                if r in m:
                    u=False
                    break
                m[r]=1
                n1=int(n1/10)
            if u is True:
                c=c+1
            num=num+1
        return c
