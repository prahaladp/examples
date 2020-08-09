class Solution {
    Map<Integer, Map<Integer, Map<Integer, Integer>>> memoMap = new HashMap<>();
    
    public int maxScore(int[] cardPoints, int k) {
        return computePoints(cardPoints, 0, k, 0, cardPoints.length-1);
    }
    
    private int computePoints(int[] cards, int cost, int k, int s, int e) {
        if (memoMap.get(s) != null) {
            if (memoMap.get(s).get(e) != null) {
                if (memoMap.get(s).get(e).get(k) != null) {
                    return memoMap.get(s).get(e).get(k);
                }
            }
        }
        if (k <= 0) {
            return cost;
        }
        int firstK = computePoints(cards, cost + cards[s], k-1, s+1, e);
        int endK = computePoints(cards, cost + cards[e], k-1, s, e-1);
        int retVal = (firstK > endK) ? firstK : endK;
        Map<Integer, Map<Integer, Integer>> topMap = memoMap.computeIfAbsent(s, k1 -> new HashMap<>());
        Map<Integer, Integer> nextLevelMap = topMap.computeIfAbsent(e, k1 -> new HashMap<>());
        nextLevelMap.put(k, retVal);
        return retVal;
    }
}
