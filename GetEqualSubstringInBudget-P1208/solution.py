class Solution(object):
    def equalSubstring(self, s, t, maxCost):
        if len(s) == 0:
            return 0
        fi = -1
        li = -1
        cc = 0
        cl = 0
        ml = 0
        i = 0
        while i < len(s):
            # print("current index = ", i)
            nc = abs(ord(s[i]) - ord(t[i]))
            if nc + cc <= maxCost:
                # add it to the list
                cc = nc + cc
                cl = cl + 1
                # print("adding ", i, " cost ", cc)
                if cl > ml:
                    ml = cl
                if fi == -1:
                    fi = i
                    li = i
                else:
                    li = i
                i = i + 1
            else:
                if fi == -1:
                    # print("ignoring ", i)
                    i = i + 1
                    continue
                cc = cc - (abs(ord(s[fi]) - ord(t[fi])))
                # print("removing ", fi, " cost ", cc)
                if cc == 0:
                    fi = -1
                    li = -1
                    cl = 0
                else:
                    cl = cl - 1
                    fi = fi + 1
        # print("ans = ", ml)
        return ml
