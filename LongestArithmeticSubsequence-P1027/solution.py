class Solution(object):
    def longestArithSeqLength(self, A):
        """
        :type A: List[int]
        :rtype: int
        """
        mp={}
        if len(A) == 1 or len(A) == 0:
            return len(A)
        l = 0
        ans=[{} for i in range(len(A))]
        for i in range(len(A)):
            for j in range(i):
                if A[i]-A[j] in ans[j]:
                    ans[i][A[i]-A[j]]=1+ans[j][A[i]-A[j]]
                else:
                    ans[i][A[i]-A[j]]=1
                if ans[i][A[i]-A[j]] > l:
                    l=ans[i][A[i]-A[j]]
            #print(i, ans[i])
        return l+1
