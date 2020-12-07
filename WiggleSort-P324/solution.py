class Solution(object):
    def wiggleSort(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        nums.sort(reverse=True)
        r=list(nums)
        s=0
        i = 1
        while s < len(nums)/2:
            r[i]=nums[s]
            s=s+1
            i=i+2
        i=0
        s=len(nums)/2
        while s < len(nums):
            r[i]=nums[s]
            i=i+2
            s=s+1
        for i in range(0, len(nums)):
            nums[i] = r[i]
