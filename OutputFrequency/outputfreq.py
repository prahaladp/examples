
def output_freq(arr, n):
    result = []
    dict = {}

    for elem in arr:
        if elem in dict:
            dict[elem] = dict[elem] + 1
            if dict[elem] >= n:
                if elem not in result:
                    result.append(elem)
                    if len(result) >= n:
                        print 'output based on frequency is ', result
                        return
        else:
            dict[elem] = 1

arr = [0,0,100,3,5,4,6,4,2,100,2,100]
output_freq(arr, 2)
output_freq(arr, 3)
output_freq(arr, 4)
