class Solution(object):
    def isPowerOfFour(self, num):
        """
        :type num: int
        :rtype: bool
        """
        while num > 1:
            if num % 4 != 0:
                return False
            num = num / 4
        if num == 1:
            return True
