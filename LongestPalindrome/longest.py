class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        st=0
        en=0
        ml=1
        n=len(s)
        mat=[]
        for i in range(n+1):
            x = [0 for i in range(n+1)]
            mat.append(x)

        for l in range(n):
            for i in range(n+1):
                if l == 0:
                    mat[i][i] = 1
                elif i+l < n:
                    if s[i] == s[i+l]:
                        if l == 1 or mat[i+1][i+l-1] == 1:
                            mat[i][i+l]=1
                            if l >= ml:
                                ml=l
                                st=i
                                en=i+l
                    else:
                        mat[i][i+l]=0
        return s[st:en+1]

s = Solution()
#print s.longestPalindrome("babad")
#print s.longestPalindrome("cbbd")
#print s.longestPalindrome("a")
print s.longestPalindrome("caba")
