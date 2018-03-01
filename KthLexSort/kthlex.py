import sys

class KthLex(object):
    def __init__(self):
        pass

    def countk(self, index, inlist, k):
        bags = []
        for i in range(0, 11):
            bags.append([])

        print 'count = ', index, inlist, k

        # sort first
        for item in inlist:
            if index >= len(item):
                bags[0].append(item)
            else:
                # find the index to which to add to
                i = ord(item[index]) - ord('0') + 1
                bags[i].append(item)

        print 'bags = ', bags

        # find the cumulative counter
        count = 0
        for i in range(0, 11):
            if len(bags[i]) == 0:
                continue

            if (count + len(bags[i])) < k:
#                count = count + len(bags[i])
                k = k - len(bags[i])
                continue

            # first bucket
            if i == 0:
                return bags[i][0]

            return self.countk(index + 1, bags[i], k)

        print 'Unable to find a match'
        return None

    def getkth(self, inlist, k):
        print '-----------------------:'
        if k == 0:
            return None

        if k > len(inlist):
            return None

        return self.countk(0, inlist, k)

def main(argv):
    k = KthLex()
    print k.getkth(['1', '2', '3', '20'], 2)
    print k.getkth(['1', '2', '4', '3', '20'], 3)
    print k.getkth(['1', '2', '3', '20'], 4)

if __name__ == "__main__":
    main(sys.argv[1:])
