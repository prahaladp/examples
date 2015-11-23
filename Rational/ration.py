
def rational(num, den, sig):
    result = ''
    result += str(num/den)
    result += '.'
    lo = num % den
   
    while sig > 0:
        lo = lo * 10
        result += str(lo/den)
        sig = sig - 1
        lo = lo % den

    print 'result : ', result 

rational(20,6,4)
rational(30,7,8)
    
