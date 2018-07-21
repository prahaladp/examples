class Solution(object):
    def findLHS(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if nums == None:
            return 0
        if len(nums) == 0:
            return 0
        nums.sort()
        print nums
        
        # continue with the current index and keep track
        # if the next number is the +1 current number, get track of next number
        prev = nums[0]
        l = len(nums)
        pcnt = self.getCount(nums, l, 0)
        ind = pcnt
        maxseq = 0

        while ind < l:
            ccnt = self.getCount(nums, l, ind)
            if nums[ind] - prev == 1 or prev - nums[ind] == 1:
                seq = pcnt + ccnt
                if seq > maxseq:
                    maxseq = seq
            pcnt = ccnt
            prev = nums[ind]
            ind += ccnt

        return maxseq

    def getCount(self, nums, l, ind):
        n = nums[ind]
        ind = ind + 1
        cnt = 1
        while ind < l:
            if nums[ind] != n:
                return cnt
            cnt = cnt + 1
            ind = ind + 1
        return cnt

s = Solution()
print s.findLHS([1,3,2,2,5,2,3,7])

        
        
