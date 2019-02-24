class Solution(object):
    def sumEvenAfterQueries(self, A, queries):
        """
        :type A: List[int]
        :type queries: List[List[int]]
        :rtype: List[int]
        """
        sum = 0
        for i in A:
            if i % 2 == 0:
                sum += i
        l = []
        for q in queries:
            if q[1] >= len(A) or q[1] < 0:
                l.append(sum)
                continue
            if A[q[1]] % 2 == 0:
                sum = sum - A[q[1]]
            A[q[1]] += q[0]
            if A[q[1]] % 2 == 0:
                sum = sum + A[q[1]]
            l.append(sum)
        return l
            
