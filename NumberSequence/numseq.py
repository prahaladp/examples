
def sequence_counter(numstr, indx):
    count = 0
    cmp = numstr[indx]
    while indx < len(numstr):
        if numstr[indx] == cmp:
            count = count + 1
        else:
            return indx, count
        indx = indx + 1
    return indx, count

def sequence_next(numstr):
    indx = 0
    strlen = len(numstr)
    seq_next = ''

    while indx < strlen:
        num = numstr[indx]
        indx, count = sequence_counter(numstr, indx)
        seq_next += str(count) + str(num)

    return seq_next

def sequence(numstr, count):
    seq_next = numstr
    while count:
        seq_next = sequence_next(seq_next)
        print '%d : %d' % (count, len(seq_next))
        count = count - 1

sequence('1', 200)
