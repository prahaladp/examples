class Solution(object):
    res=[]
    def extract(self, orig, i, cn, ip):
        f=10
        n=0
        pi=i
        while i < len(orig):
            c = ord(orig[i]) - ord('0')
            ns = n*f + c
            if ns > 255:
                break
            if cn != 4:
                if len(ip) == 0:
                    newip = str(ns)
                else:
                    newip=ip+"."+str(ns)
                self.extract(orig,i+1, cn+1, newip)
            else:
                if i==len(orig)-1:
                    #valid ip
                    if len(ip) == 0:
                        newip = str(ns)
                    else:
                        newip=ip+"."+str(ns)
                    self.res.append(newip)
            if c==0 and pi==i:
                break
            n=ns
            i=i+1
        return
            
    def restoreIpAddresses(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        self.res=[]
        self.extract(s,0,1,'')
        return self.res
