class Solution(object):
    def numUniqueEmails(self, emails):
        """
        :type emails: List[str]
        :rtype: int
        """
        em = set()
        for e in emails:
            t = e.split('@')
            if len(t) != 2:
                continue
            dom = t[1]
            t = t[0].split('+')
            t = t[0].split('.')
            fname =''
            for allt in t:
                fname+=allt
            em.add(fname+'@'+dom)
        return len(em)
        
