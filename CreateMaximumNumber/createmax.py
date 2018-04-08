class Solution(object):
    def _findMax(self, nums, sind, eind, l):
        if sind > eind:
            return l

        maxv = nums[eind]
        maxi = eind
        for i in range(eind, sind - 1, -1):
            if nums[i] > maxv:
                maxv = nums[i]
                maxi = i
        return maxi

    def maxNumber(self, nums1, nums2, k):
        ind1 = 0
        ind2 = 0
        l1 = len(nums1)
        e1 = l1 - 1 
        l2 = len(nums2)
        e2 = l2 - 1

        maxnum = []
        mind1 = self._findMax(nums1, ind1, e1, l1)
        mind2 = self._findMax(nums2, ind2, e2, l2)

        while len(maxnum) < k:

            #print 'ind1 = %s, eind1 = %s' % (ind1, e1)
            #print 'ind2 = %s, eind2 = %s' % (ind2, e2)
            #print 'maxindex1 = %s, maxindex2 = %s, num = %s' % (mind1, mind2, str(maxnum))
            
            if mind1 >= l1 and mind2 >= l2:
                return maxnum

            if mind1 >= l1:
                # see if we have enough in mind2
                avail = l2 - mind2
                if avail >= (k - len(maxnum)):
                    maxnum.append(nums2[mind2])
                    ind2 = mind2 + 1
                    e2 = l2 - 1
                else:
                    e2 = mind2 - 1
                mind2 = self._findMax(nums2, ind2, e2, l2)
                continue

            if mind2 >= l2:
                avail = l1 - mind1
                if avail >= (k - len(maxnum)):
                    maxnum.append(nums1[mind1])
                    ind1 = mind1 + 1
                    e1 = l1 - 1
                else:
                    e1 = mind1 - 1
                mind1 = self._findMax(nums1, ind1, e1, l1)
                continue

            if nums1[mind1] > nums2[mind2]:
                avail = (l1 - mind1) + (l2 - ind2)
                if avail >= (k - len(maxnum)):
                    maxnum.append(nums1[mind1])
                    ind1 = mind1 + 1
                    e1 = l1 - 1
                    mind1 = self._findMax(nums1, ind1, e1, l1)
                    continue
                e1 = mind1 - 1
                mind1 = self._findMax(nums1, ind1, e1, l1)
                continue

            avail = (l2 - mind2) + (l1 - ind1)
            if avail >= (k - len(maxnum)):
                maxnum.append(nums2[mind2])
                ind2 = mind2 + 1
                e2 = l2 - 1
                mind2 = self._findMax(nums2, ind2, e2, l2)
                continue
            e2 = mind2 - 1
            mind2 = self._findMax(nums2, ind2, e2, l2)

        return maxnum

s = Solution()
a = [3, 4, 6, 5]
b = [9, 1, 2, 5, 8, 3]
print 'max (%s, %s) = %s' % (a, b, s.maxNumber(a,b,5))
a = [6, 7]
b = [6, 0, 4]
print 'max (%s, %s) = %s' % (a, b, s.maxNumber(a,b,5))
a = [3, 9]
b = [8, 9]
print 'max (%s, %s) = %s' % (a, b, s.maxNumber(a,b,3))

