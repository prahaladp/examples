#!/bin/python
import sys
import re
from collections import defaultdict
import time, datetime

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

# hack - should be t2b-relay, but 10.252.131.29 doesn't respond, so we stick to this
t2server="10.252.131.26"

with open(sys.argv[1]) as f:
    content = f.readlines()

parse=False
last_date = ''
for s in content:
    if s.find("UTC") != -1:
        if parse is True:
            date = time.strptime(last_date, "%a %b %d %H:%M:%S UTC %Y")
            epoch = datetime.datetime.fromtimestamp(time.mktime(date)).strftime('%s')

            for k in parse_str:
                l = len(data[k])
                val = 0
                if l != 0:
                    val = data[k][l - 1]
                if k == "reassemblies required":
                    if val != 0:
                        print("echo \"iaas.public_compute.us2.UCFC2Z3C.service.stats.higgs-gateway-agent.10.252.131.107.datapath.reassemblies-required %d %s\" | nc %s 2003" % (val, epoch, t2server))
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


