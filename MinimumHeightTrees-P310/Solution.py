class Solution(object):
    def findMinHeightTrees(self, n, edges):
        """
        :type n: int
        :type edges: List[List[int]]
        :rtype: List[int]
        """
        if (len(edges) == 0):
            return [0]
        
        m = defaultdict(list)
        for e in edges:
            e1 = e[0]
            e2 = e[1]
            m[e1].append(e2)
            m[e2].append(e1)
            
        while len(m.keys()) > 2:
            rem = []
            for i, (k,v) in enumerate(m.items()):
                if len(v) == 1:
                    #print "removing ", k
                    rem.append(k)
            for r in rem:
                m.get(m[r][0]).remove(r)
                del m[r]
                
        return m.keys()
