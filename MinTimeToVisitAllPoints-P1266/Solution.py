class Solution(object):
    def minTimeToVisitAllPoints(self, points):
        """
        :type points: List[List[int]]
        :rtype: int
        """
        s=0
        for i in range(1, len(points)):
            p = i -1
            mx = abs(points[p][0] - points[i][0])
            my = abs(points[p][1] - points[i][1])
            d = abs(mx-my) + min(mx, my)
            s = s + d
        return s
        
