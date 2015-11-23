'''
Given an array of integer, find the number of un-ordered pairs in that array, say given {1, 3, 2}, the answer is 1 because {3, 2} is un-ordered, and for array {3, 2, 1}, the answer is 3 because {3, 2}, {3, 1}, {2, 1}.
'''

def unorder(l):
    le = len(l)
    print 'checking list ', l
    for i in range(0,le):
        for j in range(i + 1,le):
            if l[i] > l[j]:
                print 'unordered pair (%d, %d)' % (l[i], l[j])

unorder([1,3,2])
unorder([3,2,1])
unorder([1,2,3])
