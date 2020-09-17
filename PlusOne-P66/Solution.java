class Solution {
    private int[] toArray(List<Integer> dig) {
        int[] res = new int[dig.size()];
        for (int i = 0; i < dig.size(); i++) {
            res[i] = dig.get(i);
        }
        return res;
    }
    
    public int[] plusOne(int[] digits) {
        List<Integer> di = new LinkedList<>();
        for (int j = 0; j < digits.length; j++) {
            di.add(digits[j]);
        }
        int carryover = 1;
        int i = di.size() - 1;
        
        for (i = di.size() - 1; i >= 0; i--) {
            if (di.get(i) == 9) {
                if (i == di.size()-1 || carryover == 1) {
                    di.add(i, 0);
                    di.remove(i+1);
                    carryover = 1;
                }
            } else {
                di.add(i, di.get(i) + carryover);
                di.remove(i+1);
                return toArray(di);
            }       
        }
        if (carryover == 1) {
            di.add(0, 1);
        }
        return toArray(di);
    }
}
