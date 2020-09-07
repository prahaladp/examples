class Solution(object):
    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        c={}
        for i in nums:
            if i not in c:
                c[i] = 0
            c[i] = c[i] + 1
            if c[i] > len(nums)/2:
                return i
        return None

