
def predict_impl(p, b, e, s1, s2, pl, dp1, dp2):
        if b > e:
            return s1, s2
        if b == e:
            if pl == "1":
                s1 += p[b]
                return s1, s2
            s2 += p[b]
            return s1, s2

        #"""
        if pl == "1":
            if (b,e) in dp1:
                return dp1[(b,e)][0],dp1[(b,e)][1]
        else:
            if (b,e) in dp2:
                return dp2[(b,e)][0],dp2[(b,e)][1]
        #"""

        if pl == "1":
            ns1, ns2 = predict_impl(p, b+1, e, s1+p[b], s2, "2", dp1, dp2)
            ns3, ns4 = predict_impl(p, b, e-1, s1+p[e], s2, "2", dp1, dp2)
            if ns3 >= ns4:
                dp1[(b,e)]=(ns3,ns4)
                return ns3, ns4
            if ns3 > ns1:
                dp1[(b,e)]=(ns3,ns4)
                return ns3, ns4

            dp1[(b,e)]=(ns1,ns2)
            return ns1, ns2
        ns1, ns2 = predict_impl(p, b+1, e, s1, s2+p[b], "1", dp1, dp2)
        ns3, ns4 = predict_impl(p, b, e-1, s1, s2+p[e], "1", dp1, dp2)
        if ns4 > ns3:
            dp2[(b,e)]=(ns3,ns4)
            return ns3, ns4

        if ns4 > ns2:
            dp2[(b,e)]=(ns3,ns4)
            return ns3, ns4

        dp2[(b,e)]=(ns1,ns2)
        return ns1, ns2

def predict(p):
        if len(p) <= 1:
            return True
        dp1 = {}
        dp2 = {}
        v1, v2 = predict_impl(p, 0, len(p)-1, 0, 0, "1", dp1, dp2)
        if v1 >= v2:
            return True
        return False

p = [1, 5, 2]
print "result of p is :", predict(p)

p = [1, 5, 233, 7]
print "result of p is :", predict(p)

p = [1, 1]
print "result of p is :", predict(p)

p = [1,567,1,1,99,100]
print "result of p is :", predict(p)

p = [306416,2889306,7742619,3897090,6904996,1954213,8815586,9031637,256723,4662300,3024674,5433146,8190137,5093129,9258336,3161122,3217503,1331124,9213976,8810715]
print "result of p is :", predict(p)


