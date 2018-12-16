import collections

class Solution(object):
    def numSubarraysWithSum(self, a,s):
        memo = collections.defaultdict(int)
        ans = cur = 0
        for x in a:
            memo[cur + s] += 1
            cur += x
            ans += memo[cur]
            print 'cur = ', cur
            print 'ans = ', ans
            print 'memo = ', memo
        return ans

       
s = Solution()
s.numSubarraysWithSum([1,0,1,0,1], 2) 
