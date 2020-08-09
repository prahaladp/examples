class Solution {
    Map<Integer, Map<Integer, Map<Integer, Integer>>> memoMap = new HashMap<>();
    
    public int maxScore(int[] cardPoints, int k) {
        int retVal = 0;
        int s = 0;
        int e = s + k - 1;
        int n = cardPoints.length;
        
        for (int i = s; i <= e; i++) {
            retVal += cardPoints[i];
        }
        int nxtVal = retVal;
        for (int i = 0; i < k; i++) {
            nxtVal -= cardPoints[e];
            s = (s==0) ? n - 1 : s - 1;
            e = (e==0) ? n - 1: e - 1;
            nxtVal += cardPoints[s];
            if (nxtVal > retVal) {
                retVal = nxtVal;
            }
        }
        return retVal;
    }
}
