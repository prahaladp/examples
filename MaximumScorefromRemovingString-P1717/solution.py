class Solution(object):
    def __init__(self):
        self._mc = 0
        self._seen = {}
        
    def newIndex(self, s, current):
        i = current
        while i >= 0 and s[i] == 'X':
            i = i - 1
        if i == -1:
            return False, None, None
        for j in range(current+1, len(s)):
            if s[j] == 'X':
                continue
            if (s[i] == 'b' and s[j] == 'a') or (s[i] == 'a' and s[j] == 'b'):
                return True, i, j
            return False, None, None
        return False, None, None
    
    def getMarks(self, s, i):
        for j in range(i+1, len(s)):
            if s[j] == 'X':
                continue
            break
        return i,j
    
    def checkCost(self, s, x, y, ind):
        if ''.join(s) in self._seen:
            return self._seen[''.join(s)]
        #print(''.join(s),ind)
        cc = 0
        for i in range(len(ind)):
            if s[ind[i]] == 'X':
                continue
            if s[ind[i]] == 'a':
                c = x
            else:
                c = y
            si, ei = self.getMarks(s, ind[i])
            s[si] = 'X'
            s[ei] = 'X'
            
            if ind[i] == 0:
                ns = ind[i+1:]  
            else:
                b, i1, j1 = self.newIndex(s, ind[i])
                if b is False:
                    if i == 0:
                        ns = ind[i+1:]
                    else:
                        ns = ind[:i-1] + ind[i+1:]
                else:
                    if i == 0:
                        ns = [i1] + ind[i+1:]
                    else:
                        ns = ind[:i-1] + [i1] + ind[i+1:]

            tc = c + self.checkCost(s, x, y, ns)
            #print(''.join(s),ns, tc)
            if tc > cc:
                cc = tc
            if c == x:
                s[si] = 'a'
                s[ei] = 'b'
            else:
                s[si] = 'b'
                s[ei] = 'a'
        #print(''.join(s),ind,cc)
        self._seen[''.join(s)] = cc
        return cc
                    
    def maximumGain(self, s, x, y):
        """
        :type s: str
        :type x: int
        :type y: int
        :rtype: int
        """
        ns=[]
        for i in range(len(s)-1):
            if (s[i] == 'b' and s[i+1] == 'a') or (s[i] == 'a' and s[i+1] == 'b'):
                ns.append(i)
        cc = self.checkCost(list(s), min(x,y), max(x,y), ns)
        print(self._seen)
        return cc
        
