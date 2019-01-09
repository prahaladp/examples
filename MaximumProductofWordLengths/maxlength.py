#!/usr/bin/python

# https://leetcode.com/problems/maximum-product-of-word-lengths/submissions/

import itertools

class Solution(object):
    def getProduct(self, w1, w2):
        if w1 == w2:
            return 0
        for ch in w1:
            if ch in w2:
                return 0

        return len(w1) * len(w2)

    def maxProduct(self, words):
        """
        :type words: List[str]
        :rtype: int
        """
        s = list(itertools.product(words, words))
        maxP=0

        for p in s:
            prod = self.getProduct(p[0], p[1])
            if prod > maxP:
                maxP = prod

        return maxP


s = Solution()
print s.maxProduct(["abcw","baz","foo","bar","xtfn","abcdef"])
print s.maxProduct(["a","aa","aaa","aaaa"])
    
    
