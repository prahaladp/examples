import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
  
  public int longestValidParentheses(String s) {
    int[]                   parenOpen = new int[s.length()];
    int                     lastCompleted = -1;
    Stack<Integer>          open = new Stack<>();
    Map<Integer, Integer>   lMap = new HashMap<>();
    int                     maxLen = 0;
    
    for (int i = 0; i < parenOpen.length; i++) {
      parenOpen[i] = -1;
    }
    
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        open.push(i);
        
        // store where the last completed sequence was
        parenOpen[i] = lastCompleted;
        
      } else if (s.charAt(i) == ')' && open.isEmpty() == false) {
        parenOpen[i] = open.pop();
        int lastOpen = parenOpen[i];
        int cLen = i - parenOpen[i] + 1;
        lastCompleted = i;
        
        /* see if we need to concatenate */
        if (parenOpen[lastOpen] == lastOpen - 1 &&
            lastOpen > 0) {
          /* can be concatenated */
          cLen += lMap.get(lastOpen - 1);
        }
        
        lMap.put(i, cLen);
        
        if (cLen > maxLen) {
          maxLen = cLen;
        }
      }
    }
    
    return maxLen;
  }
  
  public static void main(String[] args) {
    Solution s = new Solution();
    String[] testStr = {"()(()())(()", "())(()()", "()(()(()(()", "()(()(()(())"};
    
    for (String ts : testStr) {
      System.out.println(ts + ":" + s.longestValidParentheses(ts));
    }
  }
}
