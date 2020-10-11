class Solution(object):
    def checkPalin(self, s, i, j, c):
        while i < j:
            if s[i] != s[j]:
                if c == 0:
                    c = c + 1
                    return self.checkPalin(s,i+1,j,c) or self.checkPalin(s,i,j-1,c)
                return False
            i=i+1
            j=j-1
        return True
        
    def validPalindrome(self, s):
        """
        :type s: str
        :rtype: bool
        """
        i=0
        j=len(s)-1
        c=0
        return self.checkPalin(s, 0, j, c)
        
