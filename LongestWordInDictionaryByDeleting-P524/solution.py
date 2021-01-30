class Solution(object):
    def isSub(self, s, w):
        j = 0
        #print(s,w)
        for i in range(0, len(s)):
            #print(s[i],w[j])
            if s[i] == w[j]:
                j = j + 1
                if j == len(w):
                    return True
        #print(s,w,j)
        return False
    
    def findLongestWord(self, s, d):
        """
        :type s: str
        :type d: List[str]
        :rtype: str
        """
        if len(s) == 0:
            return ""
        ms = ""
        for w in d:
            if self.isSub(s,w) == True:
                #print(w)
                if len(w) > len(ms):
                    ms = w
                elif len(w) == len(ms) and w < ms:
                    ms = w
        return ms
