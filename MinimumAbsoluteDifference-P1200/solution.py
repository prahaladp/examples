class Solution(object):
    def minimumAbsDifference(self, arr):
        """
        :type arr: List[int]
        :rtype: List[List[int]]
        """
        res={}
        arr.sort()
        for i in range(0, len(arr)-1):
            d = abs(arr[i] - arr[i+1])
            if d not in res:
                res[d] = []
            res[d].append([arr[i],arr[i+1]])
        k = res.keys()
        k.sort()
        return res[k[0]]
                         
