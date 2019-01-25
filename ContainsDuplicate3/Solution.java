class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i=0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length && j <= (i+k); j++) {
                if ((nums[i] >=0 && nums[j] >=0) || (nums[i] < 0 && nums[j] < 0)) {
                    if (Math.abs(nums[i]-nums[j]) <= t) {
                        return true;
                    }
                } else {
                    if (nums[i] < 0) {
                        long res = (long)(nums[j])-(long)(nums[i]);
                        if (res <= t) {
                            System.out.println("" + (res) + " " + nums[j] + " " + nums[i]);
                            return true;
                        }
                    } else {
                        long res = (long)(nums[i]) - (long)(nums[j]);
                        if (res <= t) {
                         return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
