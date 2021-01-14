class Solution {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length() || A.length() == 0) {
            return false;
        }
        List<Integer> diffIndex = new ArrayList<>();
        Map<Character, Integer> cSet = new HashMap<>();

        boolean sameChar = true;        
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                diffIndex.add(i);
            }
            if (i != 0 && A.charAt(i) != A.charAt(i-1)) {
                sameChar = false;
            }
            if (cSet.get(A.charAt(i)) == null) {
                cSet.put(A.charAt(i), 1);
            } else {
                cSet.put(A.charAt(i), cSet.get(A.charAt(i)) + 1);
            }
        }
        
        if (diffIndex.size() == 0) {
            for (Map.Entry<Character, Integer> e : cSet.entrySet()) {
                if (e.getValue() > 1) {
                    return true;
                }
            }
            return false;
        }
        if (diffIndex.size() != 2) {
            return false;
        }
        
        if (A.charAt(diffIndex.get(0)) == B.charAt(diffIndex.get(1)) &&
            A.charAt(diffIndex.get(1)) == B.charAt(diffIndex.get(0))) {
            return true;
        }
        
        return false;
    }
}
