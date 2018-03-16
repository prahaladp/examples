import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Solution {
  public static String removeKdigits(String num, int k) {
    Map<String, Map<Integer, Integer>> minMap = new ConcurrentHashMap<>();
    
    int cMin = removeKDigitsImpl(num, k, minMap);
    return Integer.toString(cMin);
  }
  
  public static int removeKDigitsImpl(String num, int k, Map<String, Map<Integer, Integer>> minMap) {
    
    if (num.length() == 0) {
      return 0;
    }
    Integer cMin = Integer.parseInt(num);
    
    if (k == 0) {
      return cMin;
    }
    
    if (minMap.get(num) != null) {
      if (minMap.get(num).containsKey(k) == true) {
        return minMap.get(num).get(k);
      }
    }
    
    for (int i = 0; i < num.length(); i++) {
      StringBuilder sb = new StringBuilder(num);
      Integer newMin = removeKDigitsImpl(sb.deleteCharAt(i).toString(), k - 1, minMap);
      if (newMin < cMin) {
        cMin = newMin;
      }
    }

    Map<Integer, Integer> entryVal = minMap.computeIfAbsent(num, e -> new ConcurrentHashMap<>());
    entryVal.put(k, cMin);
    return cMin;
  }

  public static void main(String[] args) {
    System.out.println(" RemoveKDigits  : " + Solution.removeKdigits("1432219", 3));
    System.out.println(" RemoveKDigits  : " + Solution.removeKdigits("10200", 1));
    System.out.println(" RemoveKDigits  : " + Solution.removeKdigits("10", 2));

  }
}
