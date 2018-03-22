
def wiggle(n):
       s, l = wiggle_impl(n, 0)
       return l

def wiggle_impl(n, ind):
        if len(n) == 0:
            return 0
        if len(n) == 1:
            return 1
        posi = ((n[ind + 1] - n[ind]) > 0)
        i = ind + 2
        s = ind
        print "---- wiggle_impl ", s, n[s]
        while i < len(n):
            if posi is True:
                if n[i] < n[i-1]:
                    posi = False
                    i = i + 1
                    continue
                break
            if n[i] > n[i-1]:
                posi = True
                i = i + 1
                continue
            break

        if i == len(n):
            # end of the stream
            return s, i - s

        # current length of the stream
        clen = i - s
        ns, nlen = wiggle_impl(n, i)
        print "---- wiggle_impl returned ", posi, i, ns, nlen

        if posi is True:
            if n[ns] < n[i - 1]:
                return s, clen + nlen
            return s, clen + nlen - 1
        if n[ns] > n[i-1]:
            return s, clen + nlen
        return s, clen + nlen - 1

print "wiggle [1,7,4,9,2,5] : ", wiggle([1,7,4,9,2,5])
print "wiggle [1,17,5,10,13,15,10,5,16,8] : ", wiggle([1,17,5,10,13,15,10,5,16,8])
print "wiggle [1,2,3,4,5,6,7,8,9] : ", wiggle([1,2,3,4,5,6,7,8,9])
