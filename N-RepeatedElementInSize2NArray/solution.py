class Solution(object):
    def repeatedNTimes(self, A):
        """
        :type A: List[int]
        :rtype: int
        """
        d={}
        for x in A:
            if x not in d:
                d[x] = 1
            else:
                return x
        return null
