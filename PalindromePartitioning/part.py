
class Solution(object):
    mp = {}

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
        print "check_palin_part : " + str(b) + "," + str(e)
        if b > e:
            return 0
        if b == e:
            return 0
        if b in self.mp:
            if e in self.mp[b]:
                return self.mp[b][e]

        while e >= b:

            nb = self.check_palin_impl(s, b, e)
            print "new partition = " + s[b:nb]
            n = n + 1
            b = nb
            e = te

        print "number of partitions (%s) = %d" % (s[tb:te+1], n - 1)
        if b not in self.mp:
            self.mp[b] = {}
        if e not in self.mp[b]:
            self.mp[b][e] = {}
        self.mp[b][e] = n - 1
        return n - 1

    def minCut(self, s):

        mp = {}
        if len(s) <= 1:
            return 0

        if len(s) == 2:
            if s[0] == s[1]:
                return 0
            return 1

        mc = len(s)
        for i in range(0, len(s)):
            mcu = self.check_palin_part(s, 0, len(s) - 1 - i) +\
                    self.check_palin_part(s, len(s) - i, len(s) - 1) + (i != 0)
            print ">>>>>part %d (%s, %s) = %d" % (i, s[0:len(s) - 1 - i + 1], s[len(s) - i:len(s)], mc)
            if mcu < mc:
                mc = mcu

        print "-------min partition = " +  s + " : " + str(mc)


#check_palin_part("bac")
#check_palin_part("b")
#check_palin_part("baab")
#check_palin_part("aab")
s = Solution()

s.minCut("coder") 
s.minCut("efe")
s.minCut("aaabaa")
s.minCut("aab")
s.minCut("ab")
s.minCut("abc")
s.minCut("apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp")
