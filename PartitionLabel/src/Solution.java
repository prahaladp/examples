import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
  public List<Integer> partitionLabels(String S) {
    List<String> cStr = new ArrayList<>();
    if (partList(S, 0, cStr) == false) {
      return Arrays.asList(S.length());
    }
    List<Integer> cSLen = new ArrayList<>();
    for (int i = cStr.size() - 1; i >= 0; i--) {
      cSLen.add(cStr.get(i).length());
    }

    return cSLen;
  }

  public boolean partList(String S, int indx, List<String> cStr) {

    if (indx >= S.length()) {
      return true;
    }

    // start at the current index
    for (int p = indx; p < S.length(); p++) {
      boolean mFound = false;
      // check if the character is present in any of the other lists
      for (String currS : cStr) {
        for (int it = 0; it < currS.length() && mFound == false; it++) {
          char c1 = currS.charAt(it);
          char c2 = S.charAt(p);
          // System.out.println(c1 + " " + c2);

          if (c1 == c2) {
            mFound = true;
            continue;
          }
        }
      }
      if (mFound == true) {
        if (indx == 0) {
          continue;
        }
        return false;
      }
      String pStr = S.substring(indx, p + 1);

      cStr.add(0, pStr);
      if (partList(S, p + 1, cStr) == true) {
        // System.out.println("S = " + S + ", indx = " + indx + ", " + cStr);
        return true;
      }
      cStr.remove(0);
    }

    return false;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.partitionLabels("ababcbacadefegdehijhklij"));
  }
}
