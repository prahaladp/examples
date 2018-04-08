class Solution(object):
    def findLongestChain(self, pairs):
        mp = {}
        mx = 0
        pairs.sort(key=lambda tup: tup[0])  
        print "sorted pair = ", pairs
        self.findLongestChainImpl(pairs, 0, mp, mx)
        print "mp = ", mp
        return max(mp.values())
    
    def findLongestChainImpl(self, pairs, ind, mp, mx):
        if ind >= len(pairs):
            return 0

        if ind == len(pairs) - 1:
            mp[ind] = 1
            return mp[ind]

        l = self.findLongestChainImpl(pairs, ind + 1, mp, mx)

        c = ind + 1
        mp[ind] = l
        while c < len(pairs):
            if pairs[ind][1] < pairs[c][0]:
                print "comparing ", pairs[ind][1], pairs[c][0]
                if mp[c] + 1 > mp[ind]:
                    mp[ind] = mp[c] + 1
            c = c + 1

        return mp[ind]

    def findLongestChainNew(self, pairs):
        cur, res = float('-inf'), 0
        print "sorted = ", sorted(pairs, key=lambda x: x[1])
        for p in sorted(pairs, key=lambda x: x[1]):
            if cur < p[0]: cur, res = p[1], res + 1
            print "c,r ", cur, res
        return res


s = Solution()
print "Solution [[1,2], [2,3], [3,4]] = ", s.findLongestChain([[1,2], [2,3], [3,4]])
print "Solution [[3,4],[2,3],[1,2]] = ", s.findLongestChain([[3,4],[2,3],[1,2]])
print "Solution [[8,10],[4,5],[6,7],[-7,-5],[-3,6],[1,2],[0,2],[7,8],[-1,1]] = ", s.findLongestChainNew([[8,10],[4,5],[6,7],[-7,-5],[-3,6],[1,2],[0,2],[7,8],[-1,1]])

