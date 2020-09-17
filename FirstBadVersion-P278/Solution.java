public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1;
        int h = n;
        int lv = -1;
        while (l <= h) {
            int m = l + (h-l)/2;
            if (isBadVersion(m) == false) {
                l = m + 1;
                continue;
            }
            lv = m;
            h = m - 1;
        }
        return lv;
    }
}
