def can_place(in_list, n):
    l = len(in_list)
    cp_list = in_list

    for i in range(0, l):
        #print 'loop : ', cp_list
        if n == 0:
            break
        neigh = [-1, 1]
        avail = True
        if cp_list[i] == 0:
            for n_ind in neigh:
                #print i + n_ind, n
                if (i + n_ind) >=0 and (i + n_ind) < l:
                    # print '\n', i + n_ind, cp_list
                    if cp_list[i + n_ind] != 0:
                        avail = False

            if avail is True:
                #print 'placing at ', i
                cp_list[i] = 1
                #print 'list after placing ', cp_list
                n = n - 1
#            else:
                #print 'not placing at ', i

    if n > 0:
        return False
    return True

l = [1,0,0,0,0,1,0,0]
print l, can_place(l, 3)
l = [1,0,0,0,0,1,0,0]
print l, can_place(l, 4)

l = [1,0,0,1,0,0,1,0,0]
print l, can_place(l, 1)
l = [1,0,0,1,0,0,1,0,0]
print l, can_place(l, 2)
