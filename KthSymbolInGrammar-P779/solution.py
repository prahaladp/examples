class Solution(object):
    def kthGrammar(self, N, K):
        """
        :type N: int
        :type K: int
        :rtype: int
        """
        #print(N,K)
        if N == 1 and K == 1:
            return 0
        if N == 2 and K == 1:
            return 0
        if N == 2 and K == 2:
            return 1
        Kprev = (K-1)/2 + 1                 
        
        val = self.kthGrammar(N-1,Kprev)
        #print(N,K, val)
        if val == 0:
            if K%2 == 0:
                return 1
            return 0
        if K%2 == 0:
            return 0
        return 1
