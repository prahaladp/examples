class Solution(object):
    def findNumbers(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        l = [x for x in nums if len(str(x)) % 2 == 0]
        return len(l)
