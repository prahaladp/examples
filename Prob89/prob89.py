import sys

class Prob89(object):
    def __init__(self, n):
        self._n = n
        self._keys = {}

    def print_sol(self):
        self._str = ''
        self._rec(self._str, 0, 0)

    def _is_perfect(self, cstr):
        check8 = True
        for i in range(0, len(cstr)):
            if check8 is True:
                if cstr[i] != '8':
                    print "solution :" + cstr
                    return
            elif cstr[i] != '9':
                print "solution :" + cstr
                return
            if check8 is True:
                check8 = False
            else:
                check8 = True

        print cstr + " is perfect"

    def _rec(self, cstr, num8, num9):
        if len(cstr) >= self._n:
            if cstr not in self._keys:
                self._is_perfect(cstr)
                self._keys[cstr] = 0
            return

        if num8 == 0:
            cstr += '8'
            self._rec(cstr, 1, 0)
            return

        # we can insert a 8 or a 9
        cstr += '8'
        self._rec(cstr, num8+1, num9)
        # remove the inserted 8
        cstr = cstr[:len(cstr) - 1]

        cstr = '8' + cstr
        self._rec(cstr, num8+1, num9)
        cstr=cstr[1:len(cstr)]

        if num8 > num9:
            cstr += '9'
            self._rec(cstr, num8, num9+1)
            cstr = cstr[:len(cstr) - 1]

def main(argv):
    if len(argv) <= 1:
        print "usage : " + argv[0] + " <number>"
        return

    c89 = Prob89(int(argv[1]))
    c89.print_sol()

if __name__ == "__main__":
    main(sys.argv)
