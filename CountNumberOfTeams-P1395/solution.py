class Solution(object):
    def numTeams(self, rating):
        """
        :type rating: List[int]
        :rtype: int
        """
        combi = 0
        for i in range(len(rating)):
            for j in range(i+1, len(rating)):
                if rating[j] <= rating[i]:
                    continue
                for k in range(j+1, len(rating)):
                    if rating[k] <= rating[j]:
                        continue
                    combi = combi+1
        for i in range(len(rating)):
            for j in range(i+1,len(rating)):
                if rating[j] >= rating[i]:
                    continue
                for k in range(j+1,len(rating)):
                    if rating[k] >= rating[j]:
                        continue
                    combi = combi+1
        return combi

