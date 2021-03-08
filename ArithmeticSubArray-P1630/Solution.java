class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> bools = new LinkedList<>();
        for (int i = 0; i < l.length; i++) {
            int[] nn = new int[r[i]-l[i]+1];
            if (nn.length <= 1) {
                bools.add(false);
                continue;
            }
            for (int j=l[i], ind=0; j<= r[i]; j++, ind++) {
                nn[ind] = nums[j];
            }
            Arrays.sort(nn);
            int diff = nn[1]-nn[0];
            boolean b = true;
            for (int i1 = 2;i1 < nn.length; i1++) {
                if (nn[i1]-nn[i1-1] != diff) {
                    b = false;
                    break;
                }
            }
            bools.add(b);
        }
        return bools;
    }
}
