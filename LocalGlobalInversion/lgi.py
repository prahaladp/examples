
class Solution(object):
        def isIdealPermutation(self, A):
            gi = {}
            li = {}
            if len(A) <= 1:
                return True
            gil, lil = self.checkPerm(A, 0, gi, li)
            if gil == lil:
                return True
            return False

        def checkPerm(self, A, ind, gi, li):
            #print "checkPerm %s %d %s %s" % (A, ind, gi, li)
            if ind in gi:
                return gi[ind], li[ind]

            if len(A) - 1 - ind < 2:
                if A[ind] > A[ind + 1]:
                    li[ind] = 1
                    gi[ind] = 1
                else:
                    li[ind] = 0
                    gi[ind] = 0
                li[ind + 1] = 0
                gi[ind + 1] = 0
                return gi[ind], li[ind]

            gil, lil = self.checkPerm(A, ind + 1, gi, li)
            #print "checkPerm %s %d %s %s" % (A, ind, gi, li)

            tind = ind + 1
            while tind < len(A):
                if A[ind] > A[tind]:
                    gil = gil + 1
                tind = tind + 1

            if A[ind] > A[ind + 1]:
                lil = lil + 1
            li[ind] = lil
            gi[ind] = gil
            #print ">> end checkPerm %s %d %s %s" % (A, ind, gi, li)

            return gi[ind], li[ind]

s = Solution()
gil = s.isIdealPermutation([1,2,0])
print "[1,2,0] gil = %d" % (gil)
gil = s.isIdealPermutation([1,0,2])
print "[1,0,2] gil = %d" % (gil)
gil = s.isIdealPermutation([2,0,1])
print "[2,0,1] gil = %d" % (gil)


