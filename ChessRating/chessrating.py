import math
import sys
import getopt

class Rating(object):
    def __init__(self, r):
        self._rating = r

    def _nr(self):
        diff2 = (2569 - self._rating) * (2569 - self._rating)
        nrdenom = (float)(math.sqrt(0.662 + 0.00000739 * diff2))
        nr = (float)(50) / (float)(nrdenom)
        if nr >= 45.0:
            return 45
        return nr

    def _K(self, ne, m):
        return (float)(800)/(float)(ne + m)

    def _we(self, ropp):
        exp = (float)((0 - (self._rating - ropp))/(float)(400))
        we = math.pow(10, exp) + 1
        we = (float)(1) / (float)(we)
        print " *** winning expectancy against " + str(ropp) + " is " + str(we)
        return we

    def _KSE(self, K, E, lr):
        S = sum([i[1] for i in lr])
        return (float) (K * (S - E))

    def _tm(self, T, m):
        return (float) (T) * (float)(math.sqrt(m))

    def update(self, lr):
        nr = self._nr()
        print "NR = " + str(nr)
        K = self._K(nr, len(lr))
        print "K = " + str(K)
        E = sum([self._we(opp[0]) for opp in lr])
        print "E = " + str(E)
        KSE = self._KSE(K, E, lr)
        print "KSE = " + str(KSE)
        TM = self._tm(12, len(lr))
        print "TM = " + str(TM)
        Score = sum([i[1] for i in lr])
        print "Score = " + str(Score)

        B = 0
        if KSE > TM:
            B = (float)(KSE - TM)
            print "Bonus = " + str(B)

        self._rating = self._rating + (float)(KSE) + B
        print "new rating = " + str(self._rating)

def main(argv):
    print "args = " + str(argv)
    try:
        opts, args = getopt.getopt(argv[1:], "g:r:")
    except:
        print 'chess rating rating=<rating> game=<> game=<>'
        #sys.exit(2)
    
    rating = None
    game = []
    print opts
    for opt, arg in opts:
        if opt in ("-r", "--rating"):
            rating = Rating((int)(arg))
        elif opt in ("-g", "--game"):
            tok = arg.split(',')
            game.append((int(tok[0]),float(tok[1])))
    rating.update(game)

# sample usage :
# chessrating.py -r 1225 -g 1455,0 -g 1295,0.5 -g 891,1 -g 1200,1
if __name__ == "__main__":
    main(sys.argv)
