class Solution(object):
    def printVertically(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        tok = s.split(' ')
        ind = 0
        res = []
        #print(tok)
        while True:
            ns = ''
            for t in tok:
                if ind < len(t):
                    ns += t[ind]
                else:
                    ns += ' '
            ns = ns.rstrip()
            if len(ns) == 0:
                break
            res.append(ns)
            ind = ind +1
        return res
        
