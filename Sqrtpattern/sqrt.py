
lookup_table = {}

def gen_default_list(num):
    i = 0
    pat_list = []

    while i < num:
        pat_list.append(1)
        i = i + 1

    return pat_list

def update_table(num, pat_list):
    if num in lookup_table:
        val = lookup_table[num]
        if len(val) > len(pat_list):
            lookup_table[num] = pat_list
        return
    lookup_table[num] = pat_list

def get_best_len_from_table(num):
    if num in lookup_table:
        return lookup_table[num]
    return None

def best_pattern(num):
    pat_list = get_best_len_from_table(num)
    if pat_list is not None:
        return pat_list

    pat_list = gen_default_list(num)

    curr = 2
    while curr * curr <= num:
        curr_list = []
        curr_list.append(curr)
        sub_list = best_pattern(num - (curr * curr))
        update_table(num - (curr * curr), sub_list)

        curr_list = curr_list + sub_list

        if len(curr_list) < len(pat_list):
            pat_list = curr_list

        curr = curr + 1

    return pat_list

i = 1
while i < 50000:
    pat_list = best_pattern(i)
    #print str(i) + ':' + str(pat_list)
    i = i + 1
