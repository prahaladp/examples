class Solution(object):
    def compress(self, chars):
        """
        :type chars: List[str]
        :rtype: int
        """
        p = None
        ind = 0
        count = 0
        for c in chars:
            if p is None:
                p = c
                count = 1
            elif p == c:
                count = count + 1
            else:
                if count == 1:
                    chars[ind] = p
                    ind = ind + 1
                else:
                    chars[ind] = p
                    ind = ind + 1
                    cstr = str(count)
                    for l in cstr:
                        chars[ind] = l
                        ind = ind + 1
                count = 1
                p = c
        if p is not None and count != 0:
            chars[ind] = p
            ind = ind + 1
            if count != 1:
                cstr = str(count)
                for l in cstr:
                    chars[ind] = l
                    ind = ind + 1
        return ind
