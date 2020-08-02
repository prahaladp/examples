class Solution(object):
    def arrayPairSum(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        nums.sort()
        i = 0
        sum = 0
        while i < len(nums):
            if i == len(nums) - 1:
                break;
            sum += nums[i]
            i = i + 2
        return sum
