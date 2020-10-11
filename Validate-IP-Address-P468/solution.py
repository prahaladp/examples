class Solution(object):
    def validIPAddress(self, IP):
        """
        :type IP: str
        :rtype: str
        """
        tok = IP.split('.')
        if len(tok) == 4:
            val = self.validate_ipv4(IP, tok)
            if val == True:
                return "IPv4"
        tok = IP.split(':')
        if len(tok) == 8:
            val = self.validate_ipv6(IP, tok)
            if val == True:
                return "IPv6"
        return "Neither"
    
    def validate_ipv4(self, IP, tok):
        tok = [t.strip() for t in tok]
        if tok[0] == '0':
            return False
        for t in tok:
            n = ''
            if len(t) == 0:
                return False
            if len(t) > 1 and t[0] == '0':
                return False
            for c in t:
                if c != '0':
                    if c >= '0' and c <= '9':
                        n = n + c
                    else:
                        return False
            if len(n) == 0:
                if t != '0':
                    return False
            else:
                i = int(n)
                if i >= 0 and i <= 255:
                    continue
                return False
        return True
    
    def validate_ipv6(self, IP, tok):
        for t in tok:
            t = t.strip()
            z=True
            if len(t) ==0 or len(t) > 4:
                return False
            for c in t:
                if (c >= 'a' and c <= 'f') or (c >= 'A' and c <= 'F') or (c >= '0' and c <= '9'):
                    if c != '0':
                        z=False
                    continue
                return False
        return True
