import java.util.*;  

class Solution {
    public int numJewelsInStones(String J, String S) {
        HashSet<Character> jSet = new HashSet<>();
        for (char c : J.toCharArray()) {
            jSet.add(c);
        }
        
        int num = 0;
        for (char c : S.toCharArray()) {
            if (jSet.contains(c) == true) {
                num++;
            }
        }
        return num;
    }
}
