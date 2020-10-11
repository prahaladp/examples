class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (; left <= right; left++) {
            int i = left;
            while (i != 0) {
                int d = i % 10;
                if (d == 0) {
                    break;
                }
                if (left % d != 0) {
                    break;
                }
                i /= 10;
            }
            if (i == 0) {
                res.add(left);
            }
        }
        return res;
    }
}
