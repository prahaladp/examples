class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        int n = nums.length - 1;
        up[n] = 1;
        down[n] = 1;
        
        int mq = 1;
        for (int i = n-1; i >=0; i--) {
            up[i] = down[i] = -1;
            for (int k = i+1; k <= n; k++) {
                if (nums[k] > nums[i]) {
                    if (down[k] + 1 > up[i]) {
                        up[i] = down[k] + 1;
                        if (up[i] > mq) {
                            mq = up[i];
                        }
                    }
                } else if (nums[k] < nums[i]) {
                    if (up[k] + 1 > down[i]) {
                        down[i] = up[k] + 1;
                        if (down[i] > mq) {
                            mq = down[i];
                        }
                    }
                }
            }
            //System.out.println(i + ":" + up[i] + "," + down[i]);
        }
        return mq;
    }
}
