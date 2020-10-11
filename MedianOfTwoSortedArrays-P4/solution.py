class Solution(object):
    def findMedianSortedArrays(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: float
        """
        even = True
        if (len(nums1)+len(nums2)) % 2 == 1:
            even = False
        if even is True:
            mid = (len(nums1)+len(nums2))/2-1
        else:
            mid = (len(nums1)+len(nums2))/2
        med = 0
        i = 0
        j = 0
        ind = 0
        count = 0
        print(mid)
        while ind<=mid:
            print(i,j)
            if i < len(nums1) and j < len(nums2):
                if nums1[i] < nums2[j]:
                    nn = nums1[i]
                    i=i+1
                else:
                    nn = nums2[j]
                    j=j+1
            elif i < len(nums1):
                nn = nums1[i]
                i=i+1
            else:
                nn = nums2[j]
                j=j+1
            if ind == mid:
                med+=nn
                if even is False:
                    break
                count = count + 1
                if count == 2:
                    return float(med)/2.0
                mid=mid+1
            ind=ind+1
        return med
            
