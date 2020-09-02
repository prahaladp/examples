class TreeAncestor(object):

    def __init__(self, n, parent):
        """
        :type n: int
        :type parent: List[int]
        """
        self.n = n
        self.parent = parent
        self.ktharr = []
        self.log = self.getMaxLevel(n, parent)

        for i in range(0, n):
            self.ktharr.append([])
            self.ktharr[i].append(i)
            for j in range(1, self.log):
                if i == 0 or parent[i] == -1 or len(self.ktharr[self.parent[i]]) <= j-1 or self.ktharr[self.parent[i]][j-1] == -1:
                    break
                self.ktharr[i].append(self.ktharr[self.parent[i]][j-1])
        
    def getMaxLevel(self, n, parent):
        lvl = []
        if n == 0:
            return 0
        lvl.append(1)
        maxlvl = 1
        for i in range(1, n):
            lvl.append(lvl[parent[i]] + 1)
            if lvl[i] > maxlvl:
                maxlvl = lvl[i]
        return maxlvl
                
    def getKthAncestor(self, node, k):
        """
        :type node: int
        :type k: int
        :rtype: int
        """
        #print(self.ktharr)
        #print(self.log, node, k)
        if node >= self.n:
            return -1
        if k >= len(self.ktharr[node]):
            return -1
        return self.ktharr[node][k]


# Your TreeAncestor object will be instantiated and called as such:
# obj = TreeAncestor(n, parent)
# param_1 = obj.getKthAncestor(node,k)
