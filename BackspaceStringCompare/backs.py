class Solution(object):
    def backspaceCompare(self, S, T):
        """
        :type S: str
        :type T: str
        :rtype: bool
        """
        newS = self.remBS(S)
        newT = self.remBS(T)
        print newS, '-', newT
        if newS == newT:
            return True
        return False
        
    def remBS(self, S):
        newS = ''
        i = 0
        l = len(S)
        while i < l:
            if S[i] != '#':
                newS = newS + S[i]
                i = i + 1
                continue
            j = i
            numSpace = 0
            while j < l:
                if S[j] == '#':
                    numSpace = numSpace + 1
                else:
                    break
                j = j + 1
            cL = len(newS)
            if cL - numSpace < 0:
                newS = ''
            else:
                newS = newS[:len(newS)-numSpace]
            i = i + numSpace
        return newS

s = Solution()
s.backspaceCompare('ab#d', 'ac#d')
print s.backspaceCompare('ab##', 'c#d#')
