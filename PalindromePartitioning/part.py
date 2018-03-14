
class Solution(object):
    mp = {}
    matchcount = 0

    def check_palin_impl(self, s, b, e):
        boundarye = e
        boundaryb = b

        while e > b:
            if s[e] == s[b]:
                e = e - 1
                b = b + 1
                continue
            e = boundarye - 1
            boundarye = e
            b = boundaryb

        return boundarye + 1

    def check_palin_part(self, s, b, e):
        n = 0
        tb = b
        te = e
        #print "check_palin_part : " + str(b) + "," + str(e)
        if b > e:
            return 0
        if b == e:
            return 0

        val = self.getmp(s, b, e)
        if val is not None:
            return val

        while e >= b:

            nb = self.check_palin_impl(s, b, e)
            #print "new partition = " + s[b:nb]
            n = n + 1
            b = nb
            e = te

        #print "number of partitions (%s) = %d" % (s[tb:te+1], n - 1)
        self.updatemp(s, tb, te, n-1)
        return n - 1

    def getmp(self, s, b, e):
        k = s[b:b+e+1]
        if k in self.mp:
            self.matchcount = self.matchcount + 1
            return self.mp[k]
        return None

    def updatemp(self, s, b, e, cut):
        #print "updating :  (b,e) = " + str(b) + "," + str(e) + "," + s[b:b+e+1] + ", count = " + str(cut)
        self.mp[s[b:e+1]] = cut

    def minCutImpl(self, s, b, lens):

        # print "minCutImpl = (begin = %d, lens = %d)" % (b, lens)
        if lens <= 1:
            return 0

        if lens == 2:
            #print "len = 2, " + str(b)
            if s[b] == s[b+1]:
                return 0
            return 1

        mc = lens
        for i in range(0, lens):
#            mcu = self.minCutImpl(s, b, lens - i) + self.minCutImpl(s, b + lens - i, lens - 1) + (i!=0)
            mcu = self.check_palin_part(s, b, lens - 1 - i) + self.check_palin_part(s, lens - i, lens - 1) + (i != 0)
            # print ">>>>>part %d (%s, %s) = %d" % (i, s[b:lens - 1 - i + 1], s[lens - i:lens], mc)
            if mcu < mc:
                mc = mcu

        return mc

    def minCut(self, s):
        self.matchcount = 0
        self.mp = {}
        for i in range(0, len(s)):
            mc = self.minCutImpl(s, 0, i)
        print "-------min partition = " +  s + ": " + str(mc) + " , match = " + str(self.matchcount)
#        print "dictionary = " + str(self.mp)

s = Solution()

#4
#s.minCut("coder") 
#0
#s.minCut("efe")
#1
s.minCut("aaabaa")
#1
#s.minCut("aab")
#1
#s.minCut("ab")
#2
#s.minCut("abc")
s.minCut("apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp")
