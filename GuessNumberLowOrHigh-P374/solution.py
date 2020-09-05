# The guess API is already defined for you.
# @param num, your guess
# @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
# def guess(num):

class Solution(object):
    def guessNumber(self, n):
        """
        :type n: int
        :rtype: int
        """
        l=0
        r=n
        while l<r:
            #print(l,r)
            m=l+(r-l)/2
            if guess(l) == 0:
                return l
            if guess(r) == 0:
                return r
            res=guess(m)
            if res == 0:
                return m
            if res < 0:
                r=m-1
            else:
                l=m+1
        return m
