class Solution(object):
    def findPeakElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if nums == None:
            return 0
        for i in range(0, len(nums)):
            lpeak=False
            rpeak=False
            if i == 0 or nums[i] > nums[i-1]:
                lpeak=True
            if i == len(nums)-1 or nums[i] > nums[i+1]:
                rpeak=True
            if lpeak == True and rpeak == True:
                return i

