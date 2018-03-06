
class TextJustify(object):
    def __init__(self, s):
        self._s = s

    def insert_spaces(self, wl, l):
        tl = 0
        for ws in wl:
            tl += len(ws)

        sp_req = l - tl
        sind = 0
        for i in range(0, sp_req):
            wl[sind] += ' '
            sind = sind + 1
            if sind >= len(wl) - 1:
                sind = 0

        print 'insert_spaces : '  + str(sp_req)

        cstr = ''
        for s in wl:
            cstr += s

        print 'insert_spaces : ' + cstr
        return cstr

    def justify(self, l):
        cons = 0
        result = []
        wl = []

        for s in self._s:
            print 'consumed : ' + str(cons)
            print 'current str : ' + s
            nl = len(s) + cons
            if nl < l:
                cons += len(s) + 1
                wl += [s]
            elif nl >= l:
                ns = self.insert_spaces(wl, l)
                result += [ns]
                if len(s) >= l:
                    # chop the string here
                    chop = l
                    ind = 0
                    while chop >= l:
                        result += [s[ind:ind+l]]
                        ind = l
                        chop -= l
                    cons = chop + 1
                    wl = [s[ind:len(s)]]
                else:
                    cons = len(s) + 1
                    wl = [s]

        if len(wl):
            result += [self.insert_spaces(wl, l)]
        return result

def main():
    s = ["This", "is", "an", "example", "of", "text", "justification."]
    tj = TextJustify(s)
    res = tj.justify(16)
    for r in res:
        print r

    s = ["This", "is", "an", "example", "of", "text", "justificationwithaverylongword."]
    tj = TextJustify(s)
    res = tj.justify(35)
    for r in res:
        print r

if __name__ == "__main__":
    main()

