class Solution(object):
    def findUnsortedSubarray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) <= 1:
            return 0
        sindx = -1
        for i in range(1, len(nums)):
            if nums[i] < nums[i-1]:
                sindx = i-1
                break

        if sindx == -1:
            return 0

        while sindx-1 >= 0:
            if nums[sindx] == nums[sindx-1]:
                sindx = sindx - 1

        eindx = len(nums) - 1
        for i in range(eindx-1, -1, -1):
            if nums[i] > nums[i+1]:
                eindx=i+1
                break

        while eindx+1 < len(nums):
            if nums[eindx+1] == nums[eindx]:
                eindx = eindx + 1

        return eindx - sindx + 1
            

s = Solution()
print s.findUnsortedSubarray([2, 6, 4, 8, 10, 9, 15])

