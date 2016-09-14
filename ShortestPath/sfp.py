import os
import sys

def short_test_path(target, curr, depth, paths, actual):
    next_val = []
    prev_curr = curr
    for p in paths:
        op = p
        op_type = op['op_type']
        op_val = op['op_val']
        if op_type == '*':
            curr = curr * op_val
        elif op_type == '-':
            curr = curr - op_val
        else:
            print 'unknown operation type : ' % (op_type)
            continue
        #print 'depth = %d, target = %d, curr = %d' % (depth, target, curr)
        if curr == target:
            actual.append(p)
            print 'shortest path length = %d' % (depth)
            print 'path = ', actual
            return target
        next_val.append(curr)
        print next_val
        curr = prev_curr

    modu = [target-x if target >= x else x-target for x in next_val]

    first = next_val[0]
    second = next_val[1]
    first_op = 0
    second_op = 1
    if modu[0] > modu[1]:
        first = next_val[1]
        second = next_val[0]
        first_op = 1
        second_op = 0

    actual.append(paths[first_op])
    t = short_test_path(target, first, depth + 1, paths, actual)
    if t == target:
        return t
    actual.pop()
    t = short_test_path(target, second, depth + 1, paths, actual)
    return t

def main():
    curr = int(sys.argv[1])
    target = int(sys.argv[2])
    paths = []
    paths.append({'op_type' : '*', 'op_val' : 2})
    paths.append({'op_type' : '-', 'op_val' : 1})
    actual = []
    short_test_path(target, curr, 1, paths, actual)

if __name__ == "__main__":
    main()

