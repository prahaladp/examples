import itertools
def check_1(a, b):
    d = {}
    lamb=lambda x,y : (x,y)
    for tup in map(lamb, a, b):
        d[tup[1]] = tup[0]

    print d

    s =''
    for ch in b:
        print ch
        s = s + d[ch]

    print s
    return s==a

def check_2(a, b):
    print a, b
    print map(lambda x,y : (x,y), a, b)
    print reduce(lambda d, c : d.update({c[1] : c[0]}) or d, map(lambda x,y : (x,y), a, b), {}).items()
    print zip(a, reduce(lambda d, c : d.update({c[1] : c[0]}) or d, map(lambda x,y : (x,y), a, b), {}).items())
    print reduce(lambda o, i: o + i[1][1], zip(a, reduce(lambda d, c : d.update({c[1] : c[0]}) or d, map(lambda x,y : (x,y), a, b), {}).items()), '')

    if len(a) == len(b) and \
        reduce(
                lambda o, i: o + i[1][1],
                zip(a,
                    reduce(lambda d, c : d.update({c[1] : c[0]}) or d,
                           map(lambda x,y : (x,y), a, b), {}).items()),
                           ''
        ) == a:
        print 'returning '
        return b
    else:
        return None
           
    
print check_1('abc', 'xtz')
print check_2('abc', 'xtz')

print 'done check2'

inp = ['abc']
patterns = ["cdf", "xyz", "too", "hgfdt" ,"paa", "xyz"]

#print filter((lambda x : x is not None), itertools.starmap(check_2, itertools.product(inp, patterns)))
#for iter in itertools.starmap(check_2, itertools.product(inp, patterns)):
#    print iter
#map(lambda x, y : x, itertools.product(inp, patterns))

