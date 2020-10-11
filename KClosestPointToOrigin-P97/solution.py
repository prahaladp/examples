class Solution(object):
    def kClosest(self, points, K):
        """
        :type points: List[List[int]]
        :type K: int
        :rtype: List[List[int]]
        """
        dist = [sqrt(x[0]*x[0] + x[1]* x[1]) for x in points]
        distmap = defaultdict(list)
        i = 0
        for i in range(0, len(dist)):
            distmap[dist[i]].append(points[i])
        dist.sort()
        i = 0
        res=[]
        cl=[]
        while i < K:
            if i+len(distmap.get(dist[i])) < K:
                res += distmap.get(dist[i])
                i+=len(distmap.get(dist[i]))
                continue
            res += distmap.get(dist[i])[:K-i]
            break
        return res
                    
                            
