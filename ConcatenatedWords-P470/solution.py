class Solution(object):
    def findAllConcatenatedWordsInADict(self, words):
        """
        :type words: List[str]
        :rtype: List[str]
        """
        ws = set()
        res = []
        words.sort(key=len)
        for w in words:
            if self.dfsSearch(ws, w) == True:
                res.append(w)
            ws.add(w)
        return res
        
    def dfsSearch(self, ws, w):
        if w in ws:
            return True
        for i in range(1, len(w)):
            if w[:i] in ws and self.dfsSearch(ws, w[i:]) == True:
                return True
        return False
        
