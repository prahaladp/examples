
class Solution(object):
    def find132pattern(self, nums):

        # find the first maximum
        # if there is a lesser value, return
        # if there is new higher value, update the high
        for i in range(0, len(nums)):
            high = nums[i]
            low = nums[i]
            for j in range(i+1, len(nums)):
                if nums[j] > high:
                    high = nums[j]
                elif nums[j] < high and nums[j] > low:
                    return True

        return False


s = Solution()
print "Solution [1,3,2,0] = ", s.find132pattern([1,3,2,0])
print "Solution [1,3,4,2] = ", s.find132pattern([1,3,4,2,0])
print "Solution [1,3,4] = ", s.find132pattern([1,3,4])
print "Solution [1,3,5,4] = ", s.find132pattern([1,3,5,4])
print "Solution [1,0,1,-4,-3] = ", s.find132pattern([1,0,1,-4,-3])
