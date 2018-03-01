
def init_row_index(row_index, n):
    for i in range(0, n):
        row_index.append(0)

def find_min_row(mat, row_index, n):

    min_row = n
    for i in range (0, n):
        if row_index[i] < n:
            min_row = i
            min_val = mat[min_row][row_index[min_row]]
            break

    for i in range (min_row + 1, n):
        if row_index[i] < n:
            curr_index = row_index[i]
            curr_val = mat[i][curr_index]
            print 'comparing %d with %d' % (min_val, curr_val)
            if curr_val < min_val:
                min_row = i
                min_val = curr_val
    print 'find_min_row :', min_row
    return min_row

def sort_matrix(mat, n):
    sort_list = []

    row_index = []
    init_row_index(row_index, n)

    min_row = find_min_row(mat, row_index, n)
    while min_row < n:
        row = min_row
        col = row_index[min_row]
        next_item = mat[row][col]
        sort_list.append(next_item)
        row_index[min_row] = row_index[min_row] + 1
        print 'row_index ', row_index
        print 'sort list ', sort_list
        print 'matrix ', mat
        min_row = find_min_row(mat, row_index, n)

    return sort_list

matrix = [ 
    [20, 40, 80], 
    [5, 6, 90], 
    [45, 50, 55] 
] 

sort_list = sort_matrix(matrix, 3)
print 'sorted list is ', sort_list

