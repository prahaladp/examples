import sys

def main(argv):
    str = '1'
    dig_map = {
        '0' : '10',
        '1' : '01'
    }
    for i in range(int(argv[0])):
        new_str = ''
        print 'iteration ', i
        for c in str:
            new_str += dig_map[c]
        str = new_str

    print 'iteration = ', argv[0]
    print 'new pattern = ', str

    count = 0 
    prev_zero = False

    for c in str:
        if c == '0':
            if prev_zero == True:
                count = count + 1
            prev_zero = True
        else:
            prev_zero = False

    print 'continuous zero = ', count


if __name__ == "__main__":
    main(sys.argv[1:])
    
