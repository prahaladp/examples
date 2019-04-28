class Solution(object):
    def detectCapitalUse(self, word):
        """
        :type word: str
        :rtype: bool
        """
        allcap=0
        li = 0
        
        for i in  range(0,len(word)):
            if word[i] >= 'A' and word[i] <= 'Z':
                allcap = allcap + 1
                li = i
            
            if allcap > 0 and allcap != (i + 1):
                if allcap == 1 and li == 0:
                    continue
                return False
            
        return True

