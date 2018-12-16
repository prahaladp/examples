import sys

class Solution(object):
    def getMoneyAmount(self, n):
        """
        :type n: int
        :rtype: int
        """
        mx = []
        for i in range(0,n+1):
            d = [0] * (n+1)
            d[i] = i
            mx.append(d)
        self.printarr(mx,n+1)
        for k in range(1,n):
            for x in range(1, n+1):
                if x+k >= n+1:
                    continue
                mx[x][x+k] = -1
                if k == 1:
                    mx[x][x+k] = x 
                    continue
                self.printarr(mx,n+1)
                for y in range(x, x+k+1):
                    print x, y
                    if y + 1 >= n+1:
                        continue
                    nv = y
                    print 'left = (%d, %d), right(%d, %d)'  %(x,y,y+1,x+k)
                    if y-1-x >= 1:
                        nv = nv + mx[x][y-1]
                        print 'nv (left update) = ', nv
                    if x+k-y-1 >= 1:
                        nv = nv + mx[y+1][x+k]
                        print 'nv (right update) = ', nv
                    if mx[x][x+k] == -1:
                        mx[x][x+k] = nv
                    elif nv < mx[x][x+k]:
                        mx[x][x+k] = nv
                    self.printarr(mx, n+1)

        return mx[1][n]
 
    def printarr(self, mx, n):
        print "---"
        for i in range(0,n):
            print mx[i]
        print "---"
                    
s = Solution()
print s.getMoneyAmount(7)
