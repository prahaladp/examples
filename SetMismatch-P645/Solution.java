class Solution {
    public int[] findErrorNums(int[] nums) {
        int a = -1;
        int b = -1;
        for (int i = 0; i < nums.length;) {
            if (nums[i] != i + 1) {
                // swap
                int t = nums[i];
                if (nums[t-1] == t) {
                    a = t;
                    i++;
                } else {
                    nums[i] = nums[t-1];
                    nums[t-1] = t;
                }
            } else {
                i++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                b = i + 1;
                break;
            }
        }
        int[] ret = new int[2];
        ret[0] = a;
        ret[1] = b;
        return ret;
    }
}
