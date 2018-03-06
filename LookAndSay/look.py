import sys

class Look(object):
    def __init__(self, n):
        self._n = n

    def print_ls(self):
        cstr = '1'

        for i in range(0, self._n):
            prev = ''
            count = 0
            newstr = ''
            for j in range(0, len(cstr)):
                if prev == cstr[j]:
                    count = count + 1
                else:
                    if count != 0:
                        newstr = newstr + str(count) + prev
                    prev = cstr[j]
                    count = 1

            if count != 0:
                newstr = newstr + str(count) + prev

            print 'LookSay(' + str(self._n) + ') : ' + newstr

            cstr = newstr

def main(arg):
    l = Look(int(arg))
    l.print_ls()

if __name__ == "__main__":
    main(sys.argv[1])
