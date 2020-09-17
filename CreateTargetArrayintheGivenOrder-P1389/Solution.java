class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> target = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (index[i] >= target.size()) {
                target.add(nums[i]);
            } else {
                target.add(index[i], nums[i]);
            }
        }
        int[] res = new int[target.size()];
        int ind = 0;
        for (;ind<target.size();ind++) {
            res[ind] = target.get(ind);
        }
        return res;
    }
}
