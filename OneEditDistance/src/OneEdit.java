import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class OneEdit {
  
  public static boolean checkOneEditDistance(String a, String b) {
    int lenDiff = a.length() - b.length();
    Map<Character, Integer> aCount = new ConcurrentHashMap<>();
    Map<Character, Integer> bCount = new ConcurrentHashMap<>();
    
    if (lenDiff > 1 || lenDiff < -1) {
      return false;
    }
    
    for(char c:a.toCharArray()) {
      int count = aCount.computeIfAbsent(c, key -> 0);
      aCount.put(c, count + 1);
    }
    
    for(char c:b.toCharArray()) {
      int count = bCount.computeIfAbsent(c, key -> 0);
      bCount.put(c, count + 1);
    }

    int diff = 0;
    Set<Character> checkedChar = new HashSet<>();
    
    for(char c : aCount.keySet()) {
      
      if (bCount.get(c) == null) {
        System.out.println("Char " + c + " is not present in " + b);
        diff = diff + 1;
      } else {
        int kDiff = bCount.get(c)  - aCount.get(c);
        if (kDiff < -1 || kDiff > 1) {
          System.out.println("Char " + c + " difference in strings is more than 1 ");
          return false;
        }
        
        if (kDiff != 0) {
          diff++;
          System.out.println("Char " + c + " is different");
          if (diff > 1) {
            System.out.println("Terminating since it is more than one distance");
            return false;
          }
        }
        checkedChar.add(c);
      } 
    }
    
    for(char c : bCount.keySet()) {
      if (checkedChar.contains(c) == false) {
        diff++;
        if (diff > 1) {
          System.out.println("Char " + c + " found in " + b + " is different");
          return false;
        }
      }
    }

    return true;
  }
  
  public static void main(String[] args) {
    String testStr[][] = {{"Apple", "Banana"}, {"Pork", "Beef"}, {"Hell", "Hello"}, {"Hel", "Hello"}, 
        {"Hellow", "Hello"}, {"Hallo", "Hello"}, {"car", "dog"}, {"cat", "cats"}, {"cat", "cut"}, {"cat", "cast"},
        {"cat", "at"}, {"cat", "act"}};
    Arrays.asList(testStr).stream().forEach(testElem -> {
      System.out.println(">>>>>>>Edit distance(" + testElem[0] + "," + testElem[1] + ")" +
          OneEdit.checkOneEditDistance(testElem[0], testElem[1]));
    });
  }

}
