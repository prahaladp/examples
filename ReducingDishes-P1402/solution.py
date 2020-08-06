class Solution(object):
    def compute(self, arr, val, ni):
        narr = [ni] + arr
        nval = 0
        for i in range(1, len(narr) + 1):
            nval += narr[i-1] * i
        #print arr, ni, nval
        return nval
    
    def maxSatisfaction(self, satisfaction):
        """
        :type satisfaction: List[int]
        :rtype: int
        """
        s = satisfaction
        s.sort()
        
        if len(s) == 0:
            return 0
        if len(s) == 1:
            if s[0] <= 0:
                return 0
            return s[0]
        
        res = [x for x in s if x > 0]
        zero = [x for x in s if x == 0]
        res = zero + res
        neg = [x for x in s if x < 0]
        
        val = 0
        for i in range(1, len(res) + 1):
            val += res[i-1] * i

        i = len(neg) - 1
        while i >= 0:
            nval = self.compute(res, val, neg[i])
            if nval >= val:
                res = [neg[i]] + res
                val = nval
            i = i - 1

        return val
