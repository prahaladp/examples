
def pascal(n):
    if n == 0:
        return []
    if n == 1:
        return [1]
    if n == 2:
        return [[1], [1,1]]

    l = []
    l.append([1])
    l.append([1,1])
    for i in range(3, n + 1):
        nl = [1]
        pind = 0
        for j in range(2, i):
            nl.append(l[(i-2)][(pind)] + l[(i-2)][(pind+1)])
            pind = pind + 1
        nl.append(1)
        l.append(nl)
    return l

pl = pascal(3)
for l in pl:
    print "", l

pl = pascal(10)
for l in pl:
    print "", l
