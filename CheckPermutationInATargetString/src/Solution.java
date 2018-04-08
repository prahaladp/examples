
import java.util.HashMap;
import java.util.Map;

public class Solution {
  public boolean checkInclusion(String s1, String s2) {
    Map<Character, Integer>  chMap = new HashMap<>();
    
    if (s2.length() < s1.length()) {
      return false;
    }
    
    for (char ch : s1.toCharArray()) {
      Integer count = chMap.get(ch);
      if (count == null) {
        chMap.put(ch, 1);
      } else {
        chMap.put(ch, count + 1);
      }
    }

    for (int i=s2.length() - s1.length(); i >= 0; i--) {
      Map<Character, Integer> chMapCopy = new HashMap<>(chMap);
      for (int j = i; j < s2.length(); j++) {
        Integer cnt = chMapCopy.get(s2.charAt(j));
        if (cnt == null) {
          break;
        }
        if (cnt == 1) {
          chMapCopy.remove(s2.charAt(j));
          continue;
        }
        chMapCopy.put(s2.charAt(j), cnt - 1);
      }
      if (chMapCopy.isEmpty()) {
        return true;
      }
    }
    return false;
  }
  
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println("checkPerm (ea, aieabg) = " + s.checkInclusion("ea", "aieabg"));
    System.out.println("checkPerm (ea, aiaeabg) = " + s.checkInclusion("ea", "aiaeabg"));
    System.out.println("checkPerm (ea, aiaxebabg) = " + s.checkInclusion("ea", "aiaxebabg"));
  }
}