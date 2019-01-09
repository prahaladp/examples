class Solution(object):
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        if len(nums) < 3:
            return []
        nums.sort()
        res = set()
        n=len(nums)
        for i in range(0,n-1):
            a=nums[i]
            s=i+1
            e=n-1
            while s<e:
                b=nums[s]
                c=nums[e]
                if a+b+c==0:
                    res.add((a,b,c))
                    s=s+1
                    e=e-1
                elif a+b+c>0:
                    e=e-1
                else:
                    s=s+1
        return list(res)

