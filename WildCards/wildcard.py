import sys

def copy_str(inp):
    ns = ''.join(inp[0:-1])
    return ns

def create_pattern(inp, indx):
   
    curr = indx 
    for curr in range(indx, len(inp)): 
        if inp[curr] == '?':    
            inp = inp[0:curr] + '0' + inp[curr + 1:-1]
            create_pattern(inp, curr + 1)
            inp = inp[0:curr] + '1' + inp[curr + 1:-1]
            create_pattern(inp, curr + 1)
            return
    print inp

create_pattern(sys.argv[1], 0)
