class Solution(object):
    def dfsSearch(self, ind, indmap, memomap, endlen):
        if ind in memomap:
            return memomap[ind]
        if ind >= endlen:
            return True
        if ind not in indmap:
            memomap[ind] = False
            return False
        for t in indmap[ind]:
            if self.dfsSearch(ind + len(t), indmap, memomap, endlen) == True:
                memomap[ind] = True
                return True
        memomap[ind] = False
        return False
    
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """
        from collections import defaultdict
        indexmap = defaultdict(list)
        cover=[-1 for c in s]
        for w in wordDict:
            cind = - 1
            while True:
                cind = s.find(w, cind + 1)
                if cind != -1:
                    indexmap[cind].append(w)
                    for i in range(0, len(w)):
                        cover[cind + i] = 0
                    continue
                break
        for c in cover:
            if c == -1:
                return False
        memomap={}
        return self.dfsSearch(0, indexmap, memomap, len(s))
