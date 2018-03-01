#
# 1)Start with finding the anchor count (in this case w).
# 2) That would serve as a basis for further comparision.
# 3) Ensure that all characters are in place with the same count
# 4) If there is leftover in the string, then proceed with the same approach
#

class WolfDelayMeter(object):

    _compare = "wolf"

    def __init__(self, str):
        self._str = str
        self._len = len(str)
        self._count = 0
        self._anchor = 0

    def _find_anchor(self):

        self._anchor = 0
        while self._count < self._len:
            if self._str[self._count] != 'w':
                break

            self._anchor = self._anchor + 1
            self._count = self._count + 1

        retval = False if self._anchor == 0 else True

        return retval

    def _anchor_match(self):

        # start with 'o'
        cmp_indx = 1
        ch = self._compare[cmp_indx] 
        anch = 0
        while self._count < self._len:

            curr_ch = self._str[self._count]
            self._count = self._count + 1
            if curr_ch != ch:
                if self._anchor != anch:
                    return False

                # move to the next character
                cmp_indx = cmp_indx + 1
                if cmp_indx >= len(self._compare):
                    return True

                ch = self._compare[cmp_indx]
                anch = 0
            else:
                anch = anch + 1
                if anch > self._anchor:
                    return False

                if anch == self._anchor:
                    cmp_indx = cmp_indx + 1
                    if cmp_indx >= len(self._compare):
                        return True
                    ch = self._compare[cmp_indx]
                    anch = 0

        return False

    def _check_sub(self):
       
        if not self._find_anchor():
            return "INVALID"

        # we found the anchor, now compare the rest of the string
        if not self._anchor_match():
            return "INVALID"

        return "VALID"

    def check(self):

        while self._count < self._len:
            if self._check_sub() == "INVALID":
                return "INVALID"

        return "VALID"

testlist = [ 'wolf', 'wwolfolf', 'wolfwwoollffwwwooolllfffwwwwoooollllffff', 'flowolf']
for tl in testlist:
    wf = WolfDelayMeter(tl)
    print '%s is %s' % (tl, wf.check())
