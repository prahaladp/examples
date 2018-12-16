#!/bin/python
import sys
import re
from collections import defaultdict

# tshark -V -r ~/Downloads/frag.10.60.32.70.pcap  | grep "IPv4 Fragments" > /tmp/frag.70

with open(sys.argv[1]) as f:
    content = f.readlines()

parse_str = ['fragments dropped after timeout',
             'reassemblies required',
             'packets reassembled ok',
             'packet reassembles failed',
             'fragments received ok',
             'fragments failed',
             'fragments created',
             'conntrack']

data = defaultdict(list)
delta = defaultdict(list)

headers = ['date']
headers = headers + parse_str
for h in parse_str:
    headers.append(h + ' delta')

print headers

fo = open(sys.argv[1] + ".csv", "w+")
fo.writelines(','.join( headers ))
fo.writelines('\n')

parse=False
last_date = ''
for s in content:
    if s.find("UTC") != -1:
        if parse is True:
            print "dumping_out"
            fstr = last_date + ','
            for k in parse_str:
                l = len(data[k])
                if l != 0:
                    fstr += str(data[k][l - 1]) + ','
                else:
                    fstr += ','
            for k in parse_str:
                l = len(delta[k])
                if l != 0:
                    fstr += str(delta[k][l - 1]) + ','
                else:
                    fstr += ','
            fstr += '\n'
            fo.writelines(fstr)
        last_date=s.strip()
        parse=True
        continue

    if parse is False:
        continue

    t=re.findall('\d+', s)
    if len(t) == 0:
        continue
    for i in parse_str:
        if s.find(i) != -1:
            last_data = 0
            delta_val = 0
            len_data = len(data[i])
            if len_data != 0:
                last_data = data[i][len_data - 1]
            delta_val = long(t[0]) - last_data
            data[i].append(long(t[0]))
            delta[i].append(delta_val)

print data
print delta
fo.close()
