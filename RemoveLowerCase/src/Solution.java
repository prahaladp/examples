import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Solution {
  public String removeDuplicateLetters(String s) {
    Map<Character, Integer> charMap = new HashMap<>();
    
    for (int i = 0; i < s.length(); i++) {
      charMap.put(s.charAt(i), 1);
    }
    
    StringBuilder s1 = new StringBuilder();
    while (true) {
     Optional<Character> minCh = 
         charMap.keySet().stream().collect(Collectors.minBy(Comparator.naturalOrder()));
     if (minCh.isPresent() == false) {
       return s1.toString();
     }
     charMap.remove(minCh.get());
     s1.append(minCh.get());
    }
  }
  
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println("\"bcabc\"" + s.removeDuplicateLetters("bcabc"));
    System.out.println("\"cbacdcbc\"" + s.removeDuplicateLetters("cbacdcbc"));
  }
}
