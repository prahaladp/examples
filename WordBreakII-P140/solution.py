class Solution(object):
    def dfsSearch(self, ind, indmap, memomap, endlen):
        if ind in memomap:
            return True, memomap[ind]
        if ind >= endlen:
            return True, None
        if ind not in indmap:
            return False, None
        for t in indmap[ind]:
            a, s = self.dfsSearch(ind + len(t), indmap, memomap, endlen)
            if a == True:
                if s is not None:
                    for ans in s:
                        memomap[ind].append(t + ' ' + ans)
                else:
                    memomap[ind].append(t)
        if ind in memomap:
            return True, memomap[ind]
        return False, None
    
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
                return []
        memomap=defaultdict(list)
        self.dfsSearch(0, indexmap, memomap, len(s))
        return memomap[0]
        
