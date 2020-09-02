class Solution(object):
    def minSetSize(self, arr):
        """
        :type arr: List[int]
        :rtype: int
        """
        l=len(arr)
        d = {}
        for i in arr:
            if i not in d:
                d[i] = 0
            d[i]=d[i]+1
        d=sorted(d.items(), key=lambda x: x[1], reverse=True)
        c=0
        s=0
        for i in d:
            if i[1] + c >= l/2:
                s=s+1
                break
            c=c+i[1]
            s=s+1
        return s
        
