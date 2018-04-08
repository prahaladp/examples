import math
class Solution(object):
    def judgeSquareSum(self, c):
        sqmap = {}
        lim = (int)(math.sqrt(c))
        for i in range(0, lim + 1):
            sqmap[i * i] = 1
            if c - (i*i) in sqmap:
                print "     ", i*i, c-(i*i)
                return True

        return False

s = Solution()
print 'sqrt (10) ', s.judgeSquareSum(10)
print 'sqrt (100) ', s.judgeSquareSum(100)
print 'sqrt (25) ', s.judgeSquareSum(25)
print 'sqrt (0) ', s.judgeSquareSum(0)
print 'sqrt (102) ', s.judgeSquareSum(102)


