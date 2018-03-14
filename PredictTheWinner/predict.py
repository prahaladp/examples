
def predict_impl(p, b, e, s1, s2, pl):
        if b > e:
            return s1, s2
        if b == e:
            if pl == "1":
                s1 += p[b]
                return s1, s2
            s2 += p[b]
            return s1, s2

        if pl == "1":
            ns1, ns2 = predict_impl(p, b+1, e, s1+p[b], s2, "2")
            ns3, ns4 = predict_impl(p, b, e-1, s1+p[e], s2, "2")
            print "Scores : ", pl, ns1, ns2, ns3, ns4
            if ns3 >= ns4:
                return ns3, ns4
            return ns1, ns2
        ns1, ns2 = predict_impl(p, b+1, e, s1, s2+p[b], "1")
        ns3, ns4 = predict_impl(p, b, e-1, s1, s2+p[e], "1")
        if ns4 >= ns3:
            return ns3, ns4
        return ns1, ns2

def predict(p):
        if len(p) <= 1:
            return True
        v1, v2 = predict_impl(p, 0, len(p)-1, 0, 0, "1")
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

