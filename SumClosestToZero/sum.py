
def _sum(arr):
    l = len(arr)
    sum = 0xFFFFFFFF
    pair = []
    for i in range(0, l):
        print 'i : ', i
        for j in range(i + 1, l):
            print 'j : ', j
            if arr[j] > arr[i]:
                diff = arr[j] - arr[i]
            else:
                diff = arr[i] - arr[j]
            if diff < sum:
                sum = diff 
                pair = []
                pair.append(i)
                pair.append(j)

            j = j + 1
        i = i + 1
    return sum, pair

arr = [ 0, 5, -4, 2, 5]
s, p = _sum(arr)
print p, s
            
