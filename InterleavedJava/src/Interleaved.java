import java.util.HashMap;
import java.util.Map;

public class Interleaved {
    public boolean isInterleave(String s1, String s2, String s3) {
      int ind1 = 0;
      int ind2 = 0;
      int ind3 = 0;
      int len1 = s1.length();
      int len2 = s2.length();
      int len3 = s3.length();
      Map<String, Boolean> hist = new HashMap<>();
      
      //printf("Strings : %s, %s, Checking %s\n", s1, s2, s3);
      return (isInterleaveImpl(s1, ind1, len1,
                               s2, ind2, len2,
                               s3, ind3, len3,
                               hist));
    }
    
    public boolean isInterleaveImpl(String s1, int ind1, int len1,
        String s2, int ind2, int len2,
        String s3, int ind3, int len3,
        Map<String, Boolean> hist) {
      boolean val;
    
      //printf("s1 : %d, s2 : %d, s3 : %d\n", ind1, ind2, ind3);
      if (ind3 >= len3) {
        if (ind1 < len1 || ind2 < len2) {
            return false;
        }
        return true;
      }
      if (ind1 < len1) {
        if (s1.charAt(ind1) == s3.charAt(ind3)) {
            String key = "s1" + (ind1 + 1) + "s2" + ind2 + "s3" + (ind3 + 1);
            if (hist.get(key) == null) {
              val = isInterleaveImpl(s1, ind1 + 1, len1,
                                   s2, ind2, len2,
                                   s3, ind3 + 1, len3,
                                   hist);
              hist.put(key, val);
            } else {
              val = hist.get(key);
            }
            if (val == true) {
                return val;
            }
        }
      }
      if (ind2 < len2) {
        if (s2.charAt(ind2) == s3.charAt(ind3)) {
          String key = "s1" + ind1 + "s2" + (ind2 + 1) + "s3" + (ind3 + 1);
          if (hist.get(key) == null) {
            val = isInterleaveImpl(s1, ind1, len1,
                                   s2, ind2 + 1, len2,
                                   s3, ind3 + 1, len3,
                                   hist);
            hist.put(key, val);
          } else {
            val = hist.get(key);

          }
          if (val == true) {
                return val;
          }
        }
      }
      return false;
    }
    
    public static void main(String[] args) {
      Interleaved interL = new Interleaved();
      System.out.println(interL.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
      System.out.println(interL.isInterleave("aabcc", "dbbca", "aadbbbaccc"));

      System.out.println(interL.isInterleave("bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa",
          "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab",
          "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"));
    }
}
