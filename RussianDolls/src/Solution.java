import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Solution {
  public int maxEnvelopes(int[][] envelopes) {
    Map<Integer, Set<Integer>> cMap = new HashMap<>();
    
    for (int i = 0; i < envelopes.length; i++) {
      cMap.put(i, new HashSet<>());
      for (int j = 0; j < envelopes.length; j++) {
        if (j != i) {
          if (envelopes[j][0] > envelopes[i][0] &&
              envelopes[j][1] > envelopes[i][1]) {
            cMap.get(i).add(j);
          }
        }
      }
    }
    
    /* enumerate all the keys and recursively find the max list */
    Map<Integer, Integer> maxRussianDoll = new HashMap<>();
    int maxL = 0;
    for (Entry<Integer, Set<Integer>> entry : cMap.entrySet()) {
        int len = 1 + computeLength(envelopes, entry.getKey(), cMap, maxRussianDoll);

        if (len > maxL) {
          maxL = len;
        }
    };
    
    return maxL;
  }
  
  private int computeLength(int[][] envelopes, Integer key,
      Map<Integer, Set<Integer>> cMap, Map<Integer, Integer> maxRussianDoll) {
    if (maxRussianDoll.get(key) != null) {
      return maxRussianDoll.get(key);
    }
    
    int maxL = 0;
    for (Integer subEntry : cMap.get(key)) {
      int len = 1 + computeLength(envelopes, subEntry, cMap, maxRussianDoll);
      if (len > maxL) {
        maxL = len;
      }
    }
    
    System.out.println("For doll " + key + " max " + maxL);
    maxRussianDoll.put(key, maxL);
    return maxL;
  }
  
  public static void main(String[] args) {
    int envelopes[][] = {{5,4},{6,4},{6,7},{2,3}};
    Solution s = new Solution();
    System.out.println("Max : " + s.maxEnvelopes(envelopes));
  }
}
