class Solution(object):
    def lengthOfLastWord(self, s):
        """
        :type s: str
        :rtype: int
        """
        lastindex = -1
        word=False
        indx = 0
        last=-1
        lastlen = 0
        clen = 0
        for x in s:
            if x == ' ':               
                lastindex=indx
                word = False
                if clen != 0:
                    lastlen = clen
                clen = 0
            else:
                word = True
                clen += 1
            indx += 1
        
        if clen == 0:
            return lastlen
        return clen        
