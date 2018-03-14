
def find_dup(a):
        i = 0
        dup = set()
        while i < len(a):
            index = a[i]
            if i+1 == index:
                # it was added there already, continue
                i = i + 1
                continue
            if a[index-1] == index:
                # if there is already an entry, add to duplicate
                dup.add(a[i])
                i = i + 1
            else:
                # swap
                tmp = a[index-1]
                a[index-1] = a[i]
                a[i] = tmp
            print a

        return list(dup)

a=[10,2,5,10,9,1,1,4,3,7]
print "duplicates are : " + str(find_dup(a))
