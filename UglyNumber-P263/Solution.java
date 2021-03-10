class Solution {
    public boolean isUgly(int num) {
        Set<Integer> p = new HashSet<>();
        if (num == 0) {
            return false;
        }
        while (num != 1) {
            if (num % 2 == 0) {
                p.add(2);
                num /= 2;
                continue;
            }
            if (num % 3 == 0) {
                p.add(3);
                num /= 3;
                continue;
            }
            if (num % 5 == 0) {
                p.add(5);
                num /= 5;
                continue;
            }
            return false;
        }
        
        return true;
    }
}
