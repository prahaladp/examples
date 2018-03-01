#
# number of elements in a ascending order
# these numbers could be either next to each other or
# these numbers could in any place (x > y) and a[x] > a[y]
# 	keep track of the last known start of the sequence
#	keep track of the length of the sequence
#

def find_longest_sequence(seq):
    start = 0
    prev = seq[0]
    seqlen = 1
    best_seq_len = 1
    best_seq_start = start

    for i in range(1, len(seq)):

        if seq[i] >= prev:
            prev = seq[i]
            seqlen += 1
        else:
            if seqlen > best_seq_len:
                best_seq_len = seqlen
                best_seq_start = start
            start = i
            seqlen = 1

        prev = seq[i]

    return best_seq_len, best_seq_start

seq = [0,1,4,2,3]
blen, bseq = find_longest_sequence(seq)
print "best sequence length is %d starts at %d\n "% (blen, bseq)

seq = [16,3,5,19,10,14,12,0,15]
blen, bseq = find_longest_sequence(seq)
print "best sequence length is %d starts at %d\n "% (blen, bseq)

