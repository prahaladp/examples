
class Solution(object):
    def isinteger(self, a):
        try:
            int(a)
            return True
        except ValueError:
            return False

    def isNumber(self, s):
        stmp = s.strip(" ")
        ind, delim = self.getnumber(stmp, 0)
        if ind >= len(stmp):
            return True

        if delim == '.':
            # we found a decimal
            ind, ndelim = self.getnumber(stmp, ind)
            if ind >= len(stmp):
                return True
            if ndelim == 'e':
                ind, ndelim = self.getexpo(stmp,ind)
                if ind >= len(stmp):
                    return True
                return False

        if delim == 'e':
            ind, ndelim = self.getexpo(stmp,ind)
            if ind >= len(stmp):
                return True
        return False

    def getexpo(self, stmp, ind):
        n, d = self.getnumber(stmp, ind)
        if n >= len(stmp):
            return n,d
        return ind, None

    def getnumber(self, stmp, ind):
        while ind < len(stmp):
            if self.isinteger(stmp[ind]):
                ind = ind + 1
                continue
            if stmp[ind] == '.':
                return ind + 1, '.'
            if stmp[ind] == ' ':
                ind = ind + 1
                continue
            return ind + 1, stmp[ind]

        return ind, None


testval = ["0", "  0.1", "abc" , "1 a", "2e10"]
s = Solution() 
for t in testval:
    print "Test(%s) : (%d)" % (t, s.isNumber(t))
