class Solution {
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        
        int n = piles.length/3;
        int s = 0;
        for (int i=0, j= piles.length-2; i < n; i++,j-=2) {
            s += piles[j];
        }
        return s;
    }
}
