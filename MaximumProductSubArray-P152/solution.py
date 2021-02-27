class Solution(object):
    def maxProduct(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        ans=[]
        if len(nums) == 0:
            return 0
        A = nums
        B = nums[::-1]
        for i in range(1,len(nums)):
            if A[i-1] != 0:
                A[i] = A[i] * A[i-1]
            if B[i-1] != 0:
                B[i] = B[i] * B[i-1]
        #print(A)
        #print(B)
        mxA= max(A)
        mxB = max(B)
        return max(mxA, mxB)
