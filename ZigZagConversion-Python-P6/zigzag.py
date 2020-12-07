class Solution(object):
    def convert(self, s, numRows):
        """
        :type s: str
        :type numRows: int
        :rtype: str
        """
        o = []
        if numRows == 1:
            return s
        for i in range(0, numRows):
            o.append([])
        cr=0
        cc=0
        z=False
        for x in s:
            if z is False:
                o[cr].append(x)
                cr += 1
                if cr >= numRows:
                    cr -= 2
                    z = True
                    cc+=1
            else:
                lr = len(o[cr])
                if lr != cc:
                    while lr != cc:
                        o[cr].append(' ')
                        lr += 1
                    
                o[cr].append(x)

                for i in range(0, numRows):
                    lr = len(o[i])
                    while lr <= cc-1:
                        o[i].append(' ')
                        lr += 1

                cr -= 1
                cc += 1
                
                if cr < 0:
                    z = False
                    cr = 1
        os = ''
        #for i in range(0, numRows):
            #print o[i]
        for i in range(0, numRows):
            for x in o[i]:
                if x != ' ':
                    os += x
        return os
        
