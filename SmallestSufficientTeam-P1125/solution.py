class Solution(object):
    def smallestSufficientTeam(self, req_skills, people):
        """
        :type req_skills: List[str]
        :type people: List[List[str]]
        :rtype: List[int]
        """
        skill_set = {}
        for s in req_skills:
            skill_set[s] = set([])
        for i in range(0, len(people)):
            ps = people[i]
            for sp in ps:
                skill_set[sp].add(i)
        #print(skill_set)
        fsl = []
        smap = {}
        for s in skill_set.keys():
            if len(fsl) == 0:
                for p in skill_set[s]:
                    fsl.append({p})
                    smap[frozenset({p})] = [s]
                #print(s, ":  ", smap)
                continue
            ni = []
            for p in skill_set[s]:
                #print("fsl ", fsl)
                for l in list(fsl):
                    nl = l.union({p})
                    if nl not in fsl:
                        #print("new item ", nl)
                        fsl.append(nl)
                    if frozenset(nl) not in smap:
                        smap[frozenset(nl)] = list(smap[frozenset(l)])
                    if s not in smap[frozenset(nl)]:
                        smap[frozenset(nl)].append(s)
            #print(s, ":  ", smap)
        #print(fsl)
        
        sel = None
        ml = 0
        for l in fsl:
            tl = len(smap[frozenset(l)])
            if tl == len(req_skills):
                #print("selected :" , l)
                if sel == None:
                    sel = l
                    ml = len(sel)
                elif len(l) < len(sel):
                    sel = l
                    ml = len(sel)
                    
        return(sel)
        
