class Solution(object):
    def findSubstringInWraproundString(self, p):
        """
        :type p: str
        :rtype: int
        """
        if len(p) == 0:
            return 0
        subs=[0 for i in range(0, len(p))]
        tot=[0 for i in range(0, len(p))]
        smap=set()
        subs[len(p)-1] = 1
        tot[len(p)-1] = 1
        smap.add(p[len(p)-1])
        sindexmap=defaultdict(list)
        sindexmap[len(p)-1] = [p[len(p)-1]]
                  
        for i in range(len(p)-2,-1,-1):
            i1=ord(p[i])
            i2=ord(p[i+1])
            sindexmap[i].append(p[i])
            if i2-i1 == 1 or (p[i+1] == 'a' and p[i] == 'z'):
                #print("match ",p[i], p[i+1])
                ts = 0
                for s in sindexmap[i+1]:
                    nsub=p[i]+s
                    #print("nsub ", nsub)
                    if nsub not in smap:
                        smap.add(nsub)
                        ts = ts + 1
                    sindexmap[i].append(nsub)
                subs[i] = ts
                #print(sindexmap[i], ts, subs[i])
            tot[i] = subs[i] + tot[i+1]
            if p[i] not in smap:
                tot[i] = tot[i] + 1
                smap.add(p[i])
        #print(tot)
        #print(subs)
        #print(smap)
        return tot[0]
