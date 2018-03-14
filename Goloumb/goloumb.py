
def goloumb(n):
    if n == 0:
        return []
    curr = 1
    a = []
    count = []
    a.append(curr)
    count.append(1)
    ind = 2
    while ind <= n:
        if count[curr-1] < curr:
            count[curr-1] = count[curr-1] + 1
        else:
            curr = curr + 1
            count.append(0)
        a.append(curr)
        count[curr-1] = count[curr-1] + 1
        print "a = " + str(a)
        print "count = " + str(count)
        print "curr = " + str(curr)
        ind = ind + 1
    return a

def print_goloumb(a):
    print "---" + str(a)

print_goloumb(goloumb(2))
print_goloumb(goloumb(5))
print_goloumb(goloumb(10))

