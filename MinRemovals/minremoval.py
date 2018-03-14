import math

def find_min(a, k, b, e, d):
    if (b,e) in d:
        return d[(b,e)]
    if b > e:
        return 0
    if a[e] - a[b] <= k:
        return 0
    if b == e:
        return 0

    c1 = find_min(a, k, b + 1, e, d)
    d[(b+1,e)] = c1
    c2 = find_min(a, k, b, e - 1, d)
    d[(b,e-1)] = c2

    return min(c1 + 1, c2 + 1)


a = [1, 3, 4, 9, 10, 11, 12, 17, 20]
d = {}
print "count = %d" % (find_min(a, 4, 0, len(a) - 1, d))

a = [1, 2, 5, 6, 8]
d = {}
print "count = %d" % (find_min(a, 2, 0, len(a) - 1, d))

