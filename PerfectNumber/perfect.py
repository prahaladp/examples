class Solution(object):
    def checkPerfectNumber(self, num):
        l = [1]
        for i in range(2, num/2 + 1):
            if num % i == 0:
                l.append(i)
        if sum(l) == num:
            return True
        return False 

s = Solution()
print s.checkPerfectNumber(0)
print s.checkPerfectNumber(28)
print s.checkPerfectNumber(100)
print s.checkPerfectNumber(24036583)
