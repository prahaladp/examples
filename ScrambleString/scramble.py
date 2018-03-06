
class Scramble(object):
    def __init__(self, s1, s2):
        self._s1 = s1
        self._s2 = s2

    def scrambled_str(self, ss):
        print "scrambled_str : " + ss
        l = len(ss)
        if l == 0:
            print ">>>> zero length"
            return
        if l == 1:
            print ">>>> single " + ss
            return [ss]
        if l == 2:
            print ">>>> double " + ss + "," + ss[1:2] + ss[0:1]
            return [ss] + [ss[1:2] + ss[0:1]]

        ls = []
        for i in range(1, l):
            x = self.scrambled_str(ss[0:i])
            y = self.scrambled_str(ss[i:l])
            for xi in x:
                for yi in y:
                    xy = xi + yi
                    if xy not in ls:
                        ls += [xi + yi]
            print "ls = " + str(ls)
        return ls


    def compare(self):
        print "comparing " 
        ls = self.scrambled_str(self._s1)
        for lsi in ls:
            if lsi == self._s2:
                print ">>>> match found"
                break


def main():
    s = Scramble("great", "rgeat")
    s.compare()

if __name__ == "__main__":
    main()
