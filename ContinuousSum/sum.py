
def print_list(num_list, first, last):
    print 'sum = ', num_list[first:last + 1]

def find_sum(num_list, sum):
    last_indx = 0
    len_list = len(num_list)
    indx = last_indx
    curr_sum = 0

    while curr_sum < sum:
        if indx >= len_list or curr_sum + num_list[indx] > sum:
            # stop, backtrack and restart 
            if last_indx + 1 >= len_list:
                # time to stop, since we have reached the end
                return False
            last_indx = last_indx + 1
            indx = last_indx
            curr_sum = 0
            continue

        if curr_sum + num_list[indx] == sum:
            print_list(num_list, last_indx, indx)
            return
        else:
            curr_sum = curr_sum + num_list[indx]
            indx = indx + 1

    return True

find_sum([23, 5, 4, 7, 2, 11], 20)
find_sum([1, 3, 5, 23, 2], 8)
find_sum([1, 3, 5, 23, 2], 7)
