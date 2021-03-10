class Solution(object):
    def minSumOfLengths(self, arr, target):
        """
        :type arr: List[int]
        :type target: int
        :rtype: int
        """
        l=0
        sum=0
        res=[]
        last=None
        for i in range(0, len(arr)):
            sum = sum + arr[i]
            #print i, l, sum
            if sum == target:
                if last is None:
                    last=(l,i)
                    res.append(i-l+1)
                    print "..", res
                else:
                    if l >= last[0] and l <= last[1]:
                        # overlap
                        if i-l+1 < res[len(res)-1]:
                            res[len(res)-1]=i-l+1
                            print "..overlap..", res
                    else:
                        last=(l,i)
                        res.append(i-l+1)
                        print "..", res
                sum=0
                l=i+1
            elif sum > target:
                for k in range(l,i+1):
                    sum = sum - arr[k]
                    #print "r : ", i, k, sum
                    if sum == target:
                        if last is None:
                            last=(k+1,i)
                            res.append(i-k)
                            print "..", res
                        else:
                            if k+1 >= last[0] and k+1 <= last[1]:
                                # overlap
                                if i-k < res[len(res)-1]:
                                    res[len(res)-1]=i-k
                                    print "..overlap..", res
                                last=(k+1,i)
                            else:
                                last=(k+1,i)
                                res.append(i-k)
                                print "..", res
                        l=k+1
                        break
                    elif sum < target:
                        l=k+1
                        break
                    else:
                        l=k+1
        res.sort()
        if len(res) <= 1:
            return -1
        return res[0] + res[1]
