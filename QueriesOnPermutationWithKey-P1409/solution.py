class Solution(object):
    def processQueries(self, queries, m):
        """
        :type queries: List[int]
        :type m: int
        :rtype: List[int]
        """
        p = [i+1 for i in range(m)]
        a = []
        for i in range(len(queries)):
            for ind in range(len(p)):
                if p[ind] == queries[i]:
                    a.append(ind)
                    p = [queries[i]] + p[:ind] + p[ind+1:]
                    break
        return a
