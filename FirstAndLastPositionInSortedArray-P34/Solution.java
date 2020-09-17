class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l = 0;
        int h = nums.length - 1;
        int m = l + (h-l)/2;
        int[] range = new int[2];
        range[0] = -1;
        range[1] = -1;
        
        while (l <= h) {
            m = l + (h-l)/2;
            if (nums[m] == target) {
                break;
            }
            if (nums[m] > target) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        if (l > h) {
            return range;
        }
        if (l == m && h == m) {
            range[0] = range[1] = m;
            return range;
        }
        range[0] = m;
        range[1] = m;
        int oh = h;
        int om = m;
        while (l <= h) {
            m = l + (h-l)/2;
            if (nums[m] == target) {
                range[0] = m;
                h = m - 1;
                continue;
            }
            l = m + 1;
        }
        l = om + 1;
        h = oh;
        while (l <= h) {
            m = l + (h-l)/2;
            if (nums[m] == target) {
                range[1] = m;
                l = m + 1;
                continue;
            }
            h = m -1;
        }
        return range;
    }
}
