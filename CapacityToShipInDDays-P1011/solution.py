class Solution(object):
    def shipWithinDays(self, weights, D):
        """
        :type weights: List[int]
        :type D: int
        :rtype: int
        """
        m = max(weights)
        s = sum(weights)
        
        d = sys.maxsize
        l = m
        u = s
        while u >= l:
            mid = (u+l)/2
            d = 0
            s = 0
            for i in range(0,len(weights)):
                if s + weights[i] > mid:
                    d = d + 1
                    if d >= D:
                        break
                    s = weights[i]
                else:
                    s = s + weights[i]
            #print(l,u,mid, d,s)
            if d < D:
                u = mid-1
                lv = mid
            else:
                l = mid+1
        return lv
