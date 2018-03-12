
class Solution(object):
    def reachingPoints(self, sx, sy, tx, ty):
        d = {}
        return self.rpimpl(d, sx, sy, tx, ty)

    def rpimpl(self, d, sx, sy, tx, ty):
        print "     %d %d %d %d " % (sx, sy, tx, ty)
        pts = []
        pts.insert(0, (sx, sy))
        path = []

        while len(pts) != 0:
            print "list : " , pts
            pt = pts.pop(0)
            csx = pt[0]
            csy = pt[1]
            print "     %d %d" % (csx, csy)

            if tx == csx and ty == csy:
                print " ----- found path" 
                return True
            if csx > tx:
                if csx + csy <= ty:
                    pts.insert(0, (csx, csx + csy))
                continue
            if csy > ty:
                if csx + csy <= tx:
                    pts.insert(0, (csx + csy, csy))
                continue

            pts.insert(0, (csx, csx + csy))
            pts.insert(0, (csx + csy, csy))

        return False

s = Solution()
print "Solution(1, 1, 3, 5) : %d" % (s.reachingPoints(1, 1, 3, 5))
print "Solution(1, 1, 3, 4) : %d" % (s.reachingPoints(1, 1, 3, 4))
print "Solution(1, 1, 3453, 41231) : %d" % (s.reachingPoints(1, 1, 3453, 41231))

