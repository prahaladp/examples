class Solution {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> smap = new HashMap<>();
        Map<Character, Integer> dmap = new HashMap<>();
        
        for (int i=0; i < s1.length(); i++) {
            int c = smap.computeIfAbsent(s1.charAt(i), k -> 0);
            smap.put(s1.charAt(i), c+1);
        }
        for (int i=0; i < s2.length(); i++) {
            int c = dmap.computeIfAbsent(s2.charAt(i), k -> 0);
            dmap.put(s2.charAt(i), c+1);
        }
        
        for (Map.Entry<Character, Integer> e : smap.entrySet()) {
            if (dmap.get(e.getKey()) == null) {
                return false;
            }
            if (dmap.get(e.getKey()) < e.getValue()) {
                return false;
            }
        }
        return true;
    }
}
