class Solution(object):
    def isStarToken(self, s, slen, sindex):
        if sindex + 1 < slen:
            #print ' next char = ', s[sindex + 1]
            if s[sindex + 1] == '*':
                return True
        return False

    def laststack(self, startup, s, slen, p):
        if len(startup) == 0:
            return None

        while len(startup):
            tup = startup.pop()
            lastlen = tup[1]
            lastindx = tup[0]
            lasttoken = tup[2]
            #print 'comparing token ', p[lasttoken], slen, lastindx, lastlen + 1
            if (lastindx + lastlen) <= (slen - 1):
                if p[lasttoken] != '.':
                    #print 'index, len ', lastindx, lastlen + 1
                    if s[lastindx + lastlen] != p[lasttoken]:
                        #print 'token didnt match'
                        continue

                startup.append((lastindx, lastlen + 1, lasttoken))
                sindex = lastindx + lastlen + 1
                tindex = lasttoken + 2
                break

        if len(startup) == 0:
            return None

        #print 'returning ', lastindx + lastlen + 1, lastlen + 1, lasttoken + 2
        return (lastindx + lastlen + 1, lastlen + 1, lasttoken + 2)

    def isMatch(self, s, p):
        sindex = 0
        tindex = 0
        startup = []
        tlen = len(p)
        slen = len(s)
        print 'comparing %s -> %s' % (s, p)

        if tlen == 0 or slen == 0:
            if slen == 0 and self.isStarToken(p, tlen, tindex) == True:
                return True
            if slen == 0 and tlen == 0:
                return True
            return False

        while True:
            if self.isStarToken(p, tlen, tindex) == True:
                # consume '*', start with consuming 0 characters
                #print 'found star match ', sindex, tindex, p[tindex]
                startup.append((sindex, 0, tindex))
                tindex = tindex + 2
            else:
                if p[tindex] == '.':
                    sindex = sindex + 1
                    tindex = tindex + 1
                elif s[sindex] != p[tindex]:

                    #print 'token %c != %c' % (s[sindex], p[tindex])
                    tup = self.laststack(startup, s, slen, p) 
                    if tup is None:
                        return False

                    sindex = tup[0]
                    tindex = tup[2]
                else:
                    tindex = tindex + 1
                    sindex = sindex + 1

            #print 'sindex, tindex ', sindex, tindex
            while True:
                if tindex == tlen and sindex == slen:
                    return True

                if tindex == tlen or sindex == slen:

                    if sindex == slen and self.isStarToken(p, tlen, tindex) == True:
                        tindex = tindex + 2
                        if tindex == tlen:
                            return True
                        return False

                    #print 'getting stack ', sindex, tindex
                    tup = self.laststack(startup, s, slen, p)
                    if tup is None:
                        return False

                    sindex = tup[0]
                    tindex = tup[2]

                if sindex < slen and tindex < tlen:
                    break


        return False

def main():
    s = Solution()
    print s.isMatch("", ".*")
    print s.isMatch("", "c*")
    print s.isMatch("a", "ab*a")
    print s.isMatch("aaa", "ab*a")
    print s.isMatch("aa", "ab*a")
    print s.isMatch("", "a")
    print s.isMatch("a", "")
    print s.isMatch("", ".")
    print s.isMatch("aa","a")
    print s.isMatch("aa","aa")
    print s.isMatch("aaa","aa")
    print s.isMatch("aa", "a*")
    print s.isMatch("aa", ".*")
    print s.isMatch("ab", ".*")
    print s.isMatch("aab", "c*a*b")

    print s.isMatch("aaacb", "a*b")
    print s.isMatch("aaacb", "a*cb")
    print s.isMatch("aaacb", "a*c*b")
    print s.isMatch("aaacbd", "a*c*bd")
    print s.isMatch("aaacd", "a*c*bd")
    print s.isMatch("aaacbd", "a*c*b*d")
    print s.isMatch("a", "ab*")
    print s.isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*c")

if __name__ == "__main__":
    main()
