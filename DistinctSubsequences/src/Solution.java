import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * See : https://leetcode.com/problems/distinct-subsequences/description/
 * @author Prashanth
 *
 */
public class Solution {
    public int numDistinct(String s, String t) {
      Map<Integer, Map<Integer, Integer>> indexMap = new ConcurrentHashMap<>();
   
      return numDistinctImpl(s, 0, s.length(),
          t, 0, t.length(), indexMap);
    }
    
    private int numDistinctImpl(String s, int si, int sl, String t, int ti, int tl,
        Map<Integer, Map<Integer, Integer>> indexMap) {
      if (si >= sl || ti >= tl) {
        return 0;
      }
      
      if (indexMap.get(si) != null) {
        if (indexMap.get(si).get(ti) != null) {
          return indexMap.get(si).get(ti);
        }
      }
      
      int start = si;
      int count = 0;
      while (si < sl) {
        if (s.charAt(si) != t.charAt(ti)) {
          si++;
          continue;
        }
        
        /* found a match, end of target string,
         * no need to continue down the stack.
         * */
        if (ti == tl - 1) {
          count = count + 1;
        } else {
          count += numDistinctImpl(s, si + 1, sl, t, ti + 1, tl, indexMap);
        }
        si++;
      }
      System.out.println("Count (" + start + ", " + count + ")");
      if (indexMap.get(start) == null) {
        indexMap.put(start, new ConcurrentHashMap<>());
      }
      indexMap.get(start).put(ti, count);
      return count;
    }

  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println("Distinct rabbbit rabbit = " + s.numDistinct("rabbbit", "rabit"));
    System.out.println("Distinct rabbbitt rabbit = " + s.numDistinct("rabbbitt", "rabit"));
    System.out.println("Distinct eee eee = " + s.numDistinct("eee", "eee"));
    System.out.println("Distinct " + s.numDistinct(
        "aabdbaabeeadcbbdedacbbeecbabebaeeecaeabaedadcbdbcdaabebdadbbaeabdadeaabbabbecebbebcaddaacccebeaeedababedeacdeaaaeeaecbe", 
        "bddabdcae"));
  }

}
