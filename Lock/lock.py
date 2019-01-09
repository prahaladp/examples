#!/usr/bin/python

class Solution(object):
    def openLock(self, deadends, target):
        dead = set(deadends)
        if "0000" in dead: return -1
        dead.add("0000")
        queue = [(0,'0000')]
        while queue:
            move, lock = queue.pop(0)
            if lock == target: return move
            for i,j in enumerate(lock):
                for k in [1,-1]:
                    new = lock[:i] + `(int(j)+k)%10` + lock[i+1:]
                    if new not in dead:
                        dead.add(new)
                        queue.append((move+1, new)) 
                        print 'queue = ', queue
                        print 'dead = ', dead
        return -1


s = Solution()
s.openLock(["0201","0101","0102","1212","2002"], "0202")
