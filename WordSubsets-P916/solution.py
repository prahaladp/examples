class Solution(object):
    def wordSubsets(self, A, B):
        """
        :type A: List[str]
        :type B: List[str]
        :rtype: List[str]
        """
        inmap = defaultdict(int)
        bmap = defaultdict(int)
        for a in A:
            ae = {}
            for c in a:
                if c not in ae:
                    ae[c] = 0
                ae[c] = ae[c] + 1
            inmap[a] = ae
        #print(str(inmap))
        for b in B:
            for c in b:
                if c not in bmap:
                    bmap[c] = 0
                bmap[c] = max(bmap[c], b.count(c)) 
        #print(str(bmap))
        il = A
        res = []
        for a in A:
            add = True
            for (i,item) in enumerate(bmap.items()):
                #print(a, i, item[0], item[1], a.count(item[0]) )
                if a.count(item[0]) < item[1]:
                    add = False
                    break
            if add is True:
                res.append(a)
        return res                
            
