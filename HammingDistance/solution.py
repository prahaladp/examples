class Solution(object):
    def hammingDistance(self, x, y):
        """
        :type x: int
        :type y: int
        :rtype: int
        """
        mask = 0x80000000
        cnt = 0
        while mask != 0:
            if mask & x != mask & y:
                cnt = cnt + 1
            mask = mask >> 1
        return cnt
