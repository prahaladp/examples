class Solution(object):
    def missingNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        a = 0
        n = len(nums)
        es = n * (n + 1) / 2
        for n in nums:
            a = a + n
        return es - a
