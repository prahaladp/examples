#!/bin/python
import sys
import re
from collections import defaultdict
import pprint
from dateutil import parser

def parse_and_populate(filename):
    with open(filename) as f:
        content = f.readlines()

    parse=False
    cstack = []
    cdict = None
    for s in content:
        if s.find("BEGIN") != -1:
            if cdict != None:
                cstack.append(cdict)
            cdict = {}
            t = s.split(':')
            cdict['event'] = t[1].strip().rstrip()
        elif s.find("END") != -1:
            # need to embed the current cdict into the current top level
            if 'DTEND' in cdict:
                dtend=parser.parse(cdict['DTEND'])
                dtstart=parser.parse(cdict['DTSTART'])
                td=dtend-dtstart
                diff=float(td.seconds/float(3600))
                cdict['DDIFF'] = diff
            else:
                cdict['DDIFF'] = 1.0
            if len(cstack) == 0:
                return cdict
            else:
                tstack = cstack[len(cstack) - 1]
                tstack[cdict['event']] = cdict
                cdict = tstack
                cstack = cstack[:-1]
        else:
            t = s.split(':')
            t = [x.strip().rstrip() for x in t]
            if len(t) >= 2:
                cdict[t[0]] = ','.join(t[1:])

    return cdict

pp = pprint.PrettyPrinter(indent=4)
event_list=[]
for i in range(2, len(sys.argv)):
    caldict = parse_and_populate(sys.argv[i])
    event_list.append(caldict)

total_time = 0.0
for x in event_list:
    total_time = total_time + x['DDIFF']
print sys.argv[1], total_time
