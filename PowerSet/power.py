
class Solution(object):
        def subsets(self, nums):
            res = self.subset_impl(nums)
            res.insert(0, [])
            return res

        def subset_impl(self, nums):
            if len(nums) == 0:
                return []
            if len(nums) == 1:
                return nums
            if len(nums) == 2:
                return [[nums[0]], [nums[1]], nums]
            res = [nums]
            item = self.subset_impl(nums[1:])
            res.append([nums[0]])
            for it in item:
                if len(it) != len(nums) - 1:
                    res.append(it)
                    ai = list(it)
                    ai.insert(0, nums[0])
                    res.append(ai)
                else:
                    res.append(it)
            return res

s = Solution()
print "result [1 2 3] = ", s.subsets([1,2,3])
print "result [1 2 3 4] = ", s.subsets([1,2,3,4])

