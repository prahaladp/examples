class Solution(object):
    def measure(self, cx, x, cy, y, z, visited):
        if cx == z or cy == z or cx + cy == z:
            return True
        if (cx, cy) in visited:
            return False
        visited[(cx,cy)]=True
        #print(cx,x,cy,y,z)
        if cx < x:
            if self.measure(x, x, cy, y,z, visited) == True:
                return True
        if cy < y:
            if self.measure(cx, x, y, y,z, visited) == True:
                return True
        if cy < y:
            mxy = min(y-cy, cx)
            if self.measure(cx-mxy, x, cy+mxy, y,z, visited) == True:
                return True
        if cx < x:
            myx = min(x-cx, cy)
            if self.measure(cx+myx, x, cy-myx, y,z, visited) == True:
                return True
        if self.measure(0, x, cy, y, z, visited) == True:
            return True
        return self.measure(cx, x, 0, y, z, visited)
            
    def canMeasureWater(self, x, y, z):
        """
        :type x: int
        :type y: int
        :type z: int
        :rtype: bool
        """
        visited={}
        return self.measure(0,x,0,y,z,visited)
        
