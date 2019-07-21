class Solution(object):
    def longestIncreasingPath(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: int
        """
        nm = []
        if len(matrix) == 0:
            return 0;
        lnx = len(matrix)
        lny = len(matrix[0])
        for i in range(0, lnx+1):
            nm.append([])
            for j in range(0, lny+1):
                nm[i].append(-1)
        
        q = []
        q.append((0,0))
        sides = [(-1,0), (1,0), (0,-1), (0,1)]
        maxl = -1
        while len(q) != 0:
            xy = q.pop()
            #print 'starting with ', xy
            cmaxl = 1
            missing = 0
            for s in sides:
                nx = xy[0]+s[0]
                ny = xy[1]+s[1]
                nm[xy[0]][xy[1]] = - 1
                if nx < lnx and nx >= 0 and ny >= 0 and ny < lny:
                    if matrix[nx][ny] > matrix[xy[0]][xy[1]]:
                        if nm[nx][ny] == -1:
                            #if (nx, ny) in q:
                                #print 'removing ', (nx, ny)
                                #q.remove((nx, ny))
                            #print 'adding ', (nx, ny)
                            #if (nx, ny) not in q:
                            q.append((nx, ny))
                            missing = missing + 1
                        elif nm[nx][ny] == -2:
                            missing = missing + 1
                        elif nm[nx][ny] + 1 > cmaxl:
                            cmaxl = nm[nx][ny] + 1
                    elif nm[nx][ny] == -1:
                        #if (nx, ny) in q:
                            #print 'removing ', (nx, ny)
                        #    q.remove((nx, ny))
                        #print 'adding ', (nx, ny)
                        q.append((nx, ny))
            if missing > 0:
                nm[xy[0]][xy[1]] = -2
            else:
                nm[xy[0]][xy[1]] = cmaxl
            #print ' max ', xy, nm[xy[0]][xy[1]]
            if missing > 0:
                #if (xy[0], xy[1]) in q:
                #    q.remove(xy[0], xy[1])
                #print 'adding ', (xy)
                q.insert(0, xy)
            elif nm[xy[0]][xy[1]] > maxl:
                maxl = nm[xy[0]][xy[1]]
        return maxl
                            
                        
                    
                    
