class Solution(object):
    def smallerNumbersThanCurrent(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        n = list(nums)
        n.sort()
        ans = {}
        for i in range(0, len(n)):
            if i == 0:
                ans[n[i]] = 0
                continue
            if n[i] == n[i-1]:
                continue
            ans[n[i]] = i
        return [ans[x] for x in nums]
