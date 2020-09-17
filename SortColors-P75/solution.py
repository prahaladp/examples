class Solution(object):
    def sortColors(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        c={}
        c[0] = 0
        c[1] = 0
        c[2] = 0
        for n in nums:
            c[n] = c[n] + 1
        ind = 0
        for i in range(0, 3):
            for j in range(0, c[i]):
                nums[ind] = i
                ind = ind + 1
