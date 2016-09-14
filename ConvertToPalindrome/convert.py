import sys

#
# bread -> breadaerb
# brerd -> dbrerd
#	-> drbrerd
#	-> drebrerd
#  	-> drerbrerd
# breab -> breabaerb
# breab -> baereab

def convert_to_palin(inp):
    start = 0
    last = -1
    last_indx = len(inp) + last

    while start < last_indx:
        if inp[start] != inp[last_indx]:
            inp = inp[0:start] + inp[last_indx] + inp[start:len(inp)]
        last -= 1
        start += 1
        last_indx = len(inp) + last
        print 'current string : ', inp

convert_to_palin(sys.argv[1])
