# https://leetcode.com/problems/different-ways-to-add-parentheses/description/

import re

class Solution(object):
    def compute(self, nums, op):
        if len(op) == 0:
            return nums
        res = []
        print 'compute ', nums, op
        for i in range(0, len(op)):
            print 'i = ', i
            if i == 0:
                c1 = [nums[0]]
            else:
                c1 = self.compute(nums[0:i+1], op[0:i])
            
            c2 = self.compute(nums[i+1:], op[i+1:])
            if op[i] == '+':
                res+=(self.add(c1,c2))
            if op[i] == '-':
                res+=(self.subtract(c1,c2))
            if op[i] == '*':
                res+=(self.multiply(c1,c2))
        print 'compute ', nums, op, res
        return res

    def add(self, c1, c2):
        res = []
        for t in c1:
            for u in c2:
                res.append(t + u)
        return res

    def subtract(self, c1, c2):
        res = []
        for t in c1:
            for u in c2:
                res.append(t - u)
        return res

    def multiply(self, c1, c2):
        res = []
        for t in c1:
            for u in c2:
                res.append(t * u)
        return res

    def diffWaysToCompute(self, input):
        nums = [int(a.strip()) for a in re.split('-|[+]|[*]',input) if len(a) != 0]
        op = [a.strip() for a in re.split('\d',input) if len(a) != 0]
        return self.compute(nums, op)

s = Solution()
r = s.diffWaysToCompute('2-1-1')
print '2-1-1 : ', r

r = s.diffWaysToCompute('2*3-4*5')
print '2*3-4*5 : ', r
