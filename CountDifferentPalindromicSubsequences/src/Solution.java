import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public int countPalindromicSubsequences(String s) {
        List<HashMap<Integer, List<String>>> palinStr = new ArrayList<>();
        
        //System.out.println("length = " + s.length());
        if (s.length() == 0) {
        		return 0;
        }
        
        if (s.length() == 1) {
        		return 1;
        }
        
        // initialize the array
        for (int i = 0 ; i < s.length(); i++) {
        		palinStr.add(new HashMap<>());
        		palinStr.get(i).put(1, Arrays.asList(s.substring(i, i+1)));
        }
 
        for (int i = s.length() - 1; i >= 0; i--) {
        		for (int j = i + 1; j < s.length(); j++) {
        			if (s.charAt(i) == s.charAt(j)) {
        				List<String> mList = new ArrayList<>();
        				mList.add(s.substring(i, i+1) + s.substring(j, j+1));
        				String pre = s.substring(i, i+1);
        				String post = s.substring(j, j+1);
        				if (j != i+1) {
        					if (palinStr.get(i+1) != null) {
        						for (int k = i+1; k < j; k++) {
            						int innerLen = j - k ;
        							palinStr.get(k).entrySet()
        							.stream()
        							.filter(e -> e.getKey() <= innerLen)
        							.forEach(e -> {
        								e.getValue().stream().forEach(entry ->
        									mList.add( pre + entry.toString() + post)
        										);
        							});
        							//System.out.println(" " + pre + " " + post + " <> " + palinStr.get(i+1).values()
            						//	.stream()
            						//	.collect(Collectors.toList()));
        							//System.out.println(" mList " + mList);
        						}
        					}
        				}
        				palinStr.get(i).put(j-i+1, mList);
        			}
        		}
        		//System.out.println(" palinStr Map " + palinStr);
        }
        
        List<List<String>> allPalin = palinStr.stream().flatMap(plInd -> plInd.values().stream()).collect(Collectors.toList());
        //System.out.println("intermediate list = " + allPalin);
        List<String> allPalinStr = allPalin.stream().flatMap(e -> e.stream()).collect(Collectors.toList());
        //System.out.println("intermediate list = " + allPalinStr.stream().distinct().collect(Collectors.toList()));

        return (int) allPalinStr.stream().distinct().count();
    }
    
    public static void main(String[] args) {
    		Solution s = new Solution();
    		s.countPalindromicSubsequences("aaa");
    		s.countPalindromicSubsequences("bccb");
    		s.countPalindromicSubsequences("abaa");
    }
}
