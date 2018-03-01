import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StickerForWords {
  public int minStickers(String[] stickers, String target) {
    
    if (stickers == null || target == null) {
      return -1;
    }
    
    List<String> exWords = Arrays.asList(stickers);
    Map<String, List<List<String>>> dpMatch = new HashMap<>();
    
    List<List<String>> matchStr = matchImpl(target, exWords, dpMatch);
    
    if (matchStr == null) {
      // System.out.println("no match");
      return -1;
    }
    
    if (matchStr.size() == 0) {
      // System.out.println("match = 0");
      return -1;
    }
    
    //System.out.println(" match for " + targetWord + " in " + exWords +
    //    " is " + matchStr);
    
    return matchStr.get(0).size();
  }
  
  public static List<List<String>> matchConcat(String s,
      List<List<String>> nextMinMatch) {
    
    List<List<String>> tmpString = new LinkedList<>();
    for (List<String> n : nextMinMatch) {
      List<String> t = new LinkedList<>();
      for (String nt : n) {
        t.add(new String(nt));
      }
      tmpString.add(t);
    }

    for (List<String> n : tmpString) {
      if (n.contains(s) == false) {
        n.add(s);
      }
    }
    
    List<List<String>> minList = new LinkedList<>();
    int minLength = -1;
    for (List<String> n : tmpString) {
      if (minLength == -1) {
        minLength = n.size();
      } else {
        if (n.size() < minLength) {
          minLength = n.size();
        }
      }      
    }
    // System.out.println(" Char " + s + " nextMatches " + nextMinMatch +
    //    " minLength = " + minLength);
    for (List<String> n : tmpString) {
      if (n.size() == minLength) {
        minList.add(n);
      }
    }

    return minList;
  }
  
  public static List<List<String>> matchImpl(String targetSub,
      List<String> exWords,
      Map<String, List<List<String>>> dpMatch) {
    
    if (targetSub.length() == 0) {
      return null;
    }
    
    if (dpMatch.get(targetSub) != null) {
      return dpMatch.get(targetSub);
    }
    
    // System.out.println("matchImpl : " + targetSub + " dpMatch " + dpMatch);
        
    char ch = targetSub.charAt(0);
    for (String s : exWords ) {
        if (s.indexOf(ch) != -1) {
          List<List<String>> nextMinMatch;
          if (targetSub.length() != 1) {
            String nextSubString =
                targetSub.substring(1, targetSub.length());
            nextMinMatch = matchImpl(nextSubString, exWords, dpMatch);
          } else {
            List<String> nextWord = new LinkedList<>();
            nextWord.add(s);
            nextMinMatch = new LinkedList<>();
            nextMinMatch.add(nextWord);
          }
          
          if (nextMinMatch == null) {
            // System.out.println("NoMatch found for " + s + " in " + exWords);
            return null;
          }
          
          List<List<String>> currMatch = dpMatch.get(targetSub);

          // System.out.println(" char = " + ch + " word = " + s + " currMatch = " + currMatch  +
          //    " dpMatch = " + dpMatch);

          List<List<String>> newMatch = matchConcat(s, nextMinMatch);
          
          // System.out.println(" char = " + ch + " word = " + s + " currMatch = " + currMatch + " newMatch = " + newMatch +
          //    " dpMatch = " + dpMatch);
          if (currMatch == null) {
            dpMatch.put(targetSub, newMatch); 
          } else {
            int cLen = currMatch.get(0).size();
            int nLen = newMatch.get(0).size();
            if (nLen < cLen) {
              dpMatch.put(targetSub, newMatch);
            } else if (nLen == cLen) {
              if (currMatch.containsAll(newMatch) == false) {
                currMatch.addAll(newMatch);
              }
            }
          }
          // System.out.println(" Map = " + dpMatch);
        }
    }
    
    if (dpMatch.get(targetSub) == null) {
      return null;
    }
    
    // System.out.println("matchImpl : " + targetSub + " is " + dpMatch.get(targetSub));
    return dpMatch.get(targetSub);
  }
  
  /**
   * ["with", "example", "science"], "thehat"
   * t  <- ["with"]
   * at <- ["example"] + ["with"] = ["example", "with"]
   * hat <- ["with"] + ["example", "with"] = ["example", "with"]
   * ehat <- ["example"][""science"] +  ["example", "with"] = ["example", "with"]
   * hehat <- 
   * @param args
   */
  public static void main(String args[]) {
    
    Map<String, Integer> keyMap = new HashMap<>();
    Integer val = keyMap.computeIfAbsent("1", k -> 0);
    keyMap.put("1", 100);
    val = keyMap.computeIfAbsent("1", k -> 0);;
    
    StickerForWords sW = new StickerForWords();
    
    System.out.println(sW.minStickers(new String[]{"with", "example", "science"},
        "thehat"));
    System.out.println(sW.minStickers(new String[]{"notice", "possible"}, "basicbasic"));
    System.out.println(sW.minStickers(new String[]{""}, "basicbasic"));
    System.out.println(sW.minStickers(new String[]{"notice", "possible"}, "issop"));
    System.out.println(sW.minStickers(new String[]{"notice", "possible"}, "pppp"));
    System.out.println(sW.minStickers(new String[]{"notice", "possible"}, "ooooo"));
    System.out.println(sW.minStickers(new String[]{"notice", "possible"}, "ooooon"));
    System.out.println(sW.minStickers(new String[]{"notice", "possible"}, "ooooonb"));

    System.out.println(sW.minStickers(null, null));
    System.out.println(sW.minStickers(null, "basic"));
  }

}
