class Solution(object):
    def lengthOfLIS(self, nums):
        l = []
        l = [1 for i in range(0, len(nums))]
        l[len(nums) - 1] = 1
        maxl = 1
        maxind = len(nums) - 1
        print 'nums = ' , nums
        for i in range(len(nums)-2, -1, -1):
            l[i] = 1
            for j in range(i+1, len(nums)):
                #print 'comparing ', nums[i], nums[j]
                if nums[i] < nums[j]:
                    if l[j] + 1 > l[i]:
                        l[i] = l[j] + 1
                    if l[i] > maxl:
                        maxl = l[i]
                        maxind = i
                        #print 'max at ', i, l[i], nums[i]
            print "l:", l
        return maxl

s = Solution()
nums = [10, 9, 2, 5, 3, 7, 101, 18]
print 'max length is ', s.lengthOfLIS([1,3,6,7,9,4,10,5,6])
print 'max length is ', s.lengthOfLIS([-2,-1])
print 'max length is ', s.lengthOfLIS([10,9,2,5,3,4])
print 'max length is ', s.lengthOfLIS([4,10,4,3,8,9])
