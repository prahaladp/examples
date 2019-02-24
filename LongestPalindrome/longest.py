class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        l = len(s)
        ml = 0
        ans = ''
        for i in range(0, l):
            j = l-1
            while j >= i:
                e = self.checkPalin(s, i, j)
                if e != -1:
                    if e - i + 1 > ml:
                        ans = s[i:e+1]
                        ml = e - i + 1
                j -= 1
        return ans
    
    def checkPalin(self, s, st, l):
        print st, l
        e = l
        os = st
        while l >= st:
            print 'comparing ', s[l], s[st]
            if s[l] != s[st]:
                return -1
            st += 1
            l -= 1
        print st, e, s[os:e+1]
        return e
                        
s = Solution()
#print s.longestPalindrome("babad")
print s.longestPalindrome("cbbd")
print s.longestPalindrome("a")
