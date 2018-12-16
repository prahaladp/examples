import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Solution {
  private class Info {
    public int indx;
    public String source;
    public String target;

    public Info(int indx, String source, String target) {
      this.indx = indx;
      this.source = source;
      this.target = target;
    }
  }

  public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
    ArrayList<Info> validInfo = new ArrayList<>();

    /* first find the matches */
    for (int i = 0; i < indexes.length; i++) {
      System.out.println("Indx = " + S.substring(indexes[i], indexes[i] + sources[i].length()));
      if (S.substring(indexes[i], indexes[i] + sources[i].length()).compareTo(sources[i]) == 0) {
        System.out.println("String " + S + " Adding indx " + i);
        validInfo.add(new Info(indexes[i], sources[i], targets[i]));
      }
    }

    Comparator<Info> compInfo = new Comparator<Info>() {
      public int compare(Info a, Info b) {
        if (a.indx < b.indx) {
          return -1;
        }
        return 1;
      };
    };

    Collections.sort(validInfo, compInfo);

    StringBuilder finalStr = new StringBuilder();
    Integer prevEnd = 0;

    for (Info info : validInfo) {

      System.out.println("info = " + info);
      System.out.println("Indx = " + info.indx);

      /* copy from previous index to this */
      if (info.indx != 0) {
        System.out.println("prevEnd = " + prevEnd + " endIndex " + (info.indx));
        finalStr.append(S.substring(prevEnd, info.indx));
      }

      /* initialize the prevEnd */
      prevEnd = info.indx + info.source.length();

      /* add target */
      finalStr.append(info.target);

      System.out.println("Current string " + finalStr.toString());
    }

    /* add the final String */
    finalStr.append(S.substring(prevEnd));

    return finalStr.toString();
  }

  public static void main(String[] args) {
    {
      String S = "abcd";
      int[] indexes = {0, 2};
      String[] sources = {"ab", "ec"};
      String[] targets = {"eee", "ffff"};
      Solution s = new Solution();

      System.out
          .println("findReplaceString = " + s.findReplaceString(S, indexes, sources, targets));
    }
    {
      String S = "abcd";
      int[] indexes = {0, 2};
      String[] sources = {"a", "cd"};
      String[] targets = {"eee", "ffff"};
      Solution s = new Solution();

      System.out
          .println("findReplaceString = " + s.findReplaceString(S, indexes, sources, targets));
    }
    {
      String S = "vmokgggqzp";
      int[] indexes = {3, 5, 1};
      String[] sources = {"kg", "ggq", "mo"};
      String[] targets = {"s", "so", "bfr"};
      Solution s = new Solution();

      System.out
          .println("findReplaceString = " + s.findReplaceString(S, indexes, sources, targets));
    }

  }
}
