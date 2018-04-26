class Solution(object):
    def overflow(self, glasses, ind):
        g = []
        for i in range(ind, len(glasses)):
            for j in range(0, len(glasses[i])):
                if glasses[i][j] > 1:
                    g.append(j)
            if len(g) != 0:
                return i, g
        return i, g

    def addnewrow(self, glasses, sz):
        rg = []
        for j in range(0, sz):
            rg.append(0)
        glasses.append(rg)

    def printglass(self, glasses):
        for i in glasses:
            print  i

    def champagneTower(self, poured, query_row, query_glass):
        glasses = []
        n = 0
        g = n + 1
        self.addnewrow(glasses, g)
        for i in range(1, poured + 1):
            glasses[0][0] = glasses[0][0] + 1
            ind = 0
            while True:
                ind, gs = self.overflow(glasses, ind)
                if len(gs) == 0:
                    break
                if len(glasses) <= ind + 1:
                    self.addnewrow(glasses, ind+2)

                print gs
                for g in gs:
                    # pour 0.5 into each of the glasses in the next row
                    ng1 = g
                    ng2 = g + 1
                    print 'ind = ', ind, ng1, ng2
                    diff = glasses[ind][g] - 1
                    glasses[ind+1][ng1] = glasses[ind+1][ng1] + diff/2.0
                    glasses[ind+1][ng2] = glasses[ind+1][ng2] + diff/2.0
                    glasses[ind][g] -= diff
            self.printglass(glasses)

s = Solution()
s.champagneTower(15, 0, 0)

