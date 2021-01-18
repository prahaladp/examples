class Solution(object):
    def kConcatenationMaxSum(self, arr, k):
        """
        :type arr: List[int]
        :type k: int
        :rtype: int
        """
        if len(arr) == 0:
            return 0
        psofar=max(0,arr[0])
        msofar=max(0,arr[0])
        sz=len(arr)
        for i in range(1,len(arr)*k):
            psofar = max(arr[i%sz], psofar + arr[i%sz]) 
            msofar = max(0,max(msofar,psofar))

        if msofar < 0:
            return 0
        return msofar
        
