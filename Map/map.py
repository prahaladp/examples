import itertools

def funcA(a):
	return a * a

def funcTwo(a, b):
	return a * b

def funcThree(a, b, c):
	return a * b * c

def funcReduce(a, b):
	print 'reduce ', a, b
	return 9
	

l=[1,2,3]
c=[2,3,8]
print map(funcA, l)
print map(funcTwo, l, c)

print reduce(funcReduce, map(funcTwo, l, c))

print zip(l, c, map(funcTwo, l, c))
for i in itertools.starmap(funcThree, zip(l, c, map(funcTwo, l, c))):
	print i

iters = itertools.tee(itertools.starmap(funcThree, zip(l, c, map(funcTwo, l, c))))
for iter in iters:
	for i in iter:
		print i

print itertools.product(['abc'], ['c', 'd'])
for iter in itertools.product(['abc'], ['c', 'd']):
	print iter
