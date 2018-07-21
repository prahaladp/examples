# a3[b2[c1[d]]]e will be decompressed as abcdcdbcdcdbcdcde

class Solution(object):
    def decompress_impl(self, str, ind):
        res = ''
        factor = 0
        substr = ''
        while ind < len(str):
            a = str[ind]
            if a.isalpha() is True:
                res += a
                ind = ind + 1
            elif a == '[':
                print 'current factor = ', factor
                substr, ind = self.decompress_impl(str, ind + 1)
                print 'adding ', substr ,' ',  factor, 'times'
                for i in range(0, factor):
                    res += substr
            elif a == ']':
                return res, ind + 1
            else:
                if factor == 0:
                    factor = int(a)
                else:
                    factor = (factor * 10) + int(a)
                ind = ind + 1
        return res

    def decompress(self, str):
        return self.decompress_impl(str, 0)

s = Solution()
print 'out (a3[b2[c1[d]]]e) : ', s.decompress('a3[b2[c1[d]]]e')
