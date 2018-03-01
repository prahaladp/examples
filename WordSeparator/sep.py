import itertools

def sep(bw, wd, st, shortestlist):

    print "sep = ", bw, wd, st

    currw = "";
    for i in range(st, len(bw)):
        currw += bw[i]
        if currw in wd:
            nextlist = []
            nexlist = sep(bw, wd, i + 1)
            print "nextlist (%d) : %s" % (i, nextlist)
            print "shortestlist = ", shortestlist
            if len(shortestlist) == 0:
                shortestlist = nextlist
                shortestlist.append(currw)
                print "first shortestlist = ", shortestlist
            elif (len(nextlist) != 0) and ((len(nexlist) + 1) < len(shortestlist)):
                shortestlist = nextlist
                print "new shortestlist = ", shortestlist

    return shortestlist

print sep("ilikefacepalm", ["i", "like", "face", "facepalm", "palm"], 0, [])
