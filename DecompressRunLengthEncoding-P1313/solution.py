class Solution(object):
    def decompressRLElist(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        i = 0
        r = []
        while i < len(nums):
            x = [nums[i+1] for j in range(0,nums[i])]
            r += x
            i = i + 2
        return r
