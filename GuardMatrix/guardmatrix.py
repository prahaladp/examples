
row = 5
column = 5
matrix = [ [0 for x in range(0, row)] for y in range(0, column)]

matrix = [ ['.', '#', '.', 'G', '.'],
           ['.', '.', '#', '.', '.'],
           ['G', '.', '.', '.', '.'],
           ['.', '.', '#', '.', '.']
         ]

def compute_path(matrix, x, y, min):
    if matrix[x][y] == 'G':
        min = 1
    elif not (matrix[x][y] == '#' and matrix[x][y] == '.'):
        # nothing to do
        if (matrix[x][y] < min):
            min = matrix[x][y] + 1

    return min

def print_matrix(matrix, row, col):
    for x in range(0, row):
        print matrix[x]

def find_paths(matrix, row, col):

    need_another_pass = True

    while need_another_pass is True:
        need_another_pass = False
        for x in range(0, row):
            for y in range(0, col):
                min = 65535
                if matrix[x][y] == 'G' or matrix[x][y] == '#':
                    continue
                if (x - 1) >=0:
                    min = compute_path(matrix, x-1, y, min)
                if (x + 1) < row:
                    min = compute_path(matrix, x+1, y, min)
                if (y - 1) >=0:
                    min = compute_path(matrix, x, y-1, min)
                if (y + 1) < row:
                    min = compute_path(matrix, x, y+1, min)

                matrix[x][y] = min
                if min == 65535:
                    need_another_pass = True
            print '-------x = %d, y = %d-------' % (x,y)
            print_matrix(matrix, row, col)
                

find_paths(matrix, 4, 5)
