class Solution {
    public boolean  isHappy(int n) {
        Map<Integer, Integer> numMap = new HashMap<>();
        int sq = getSqNum(n);
        while (sq != 1) {
            if (numMap.get(sq) != null) {
                return false;
            }
            numMap.put(sq, sq);
            sq = getSqNum(sq);
        }
        return true;
    }
    private int getSqNum(int n) {
        int sq = 0;
        while (n != 0) {
            int r = n%10;
            sq += (r * r);
            n /= 10;
        }
        return sq;
    }

}
