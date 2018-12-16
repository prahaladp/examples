#!/bin/python
import sys
import re

# tshark -V -r ~/Downloads/frag.10.60.32.70.pcap  | grep "IPv4 Fragments" > /tmp/frag.70

with open(sys.argv[1]) as f:
    content = f.readlines()

for s in content:
    t=re.findall('\d+', s)
    i = 3
    frags = []
    while i < len(t):
        frags.append(int(t[i]))
        i = i + 2
    i = 1
    while i < len(frags):
         if frags[i] < frags[i-1]:
             print 'Out of order fragments : ', frags, s
         i = i + 1
