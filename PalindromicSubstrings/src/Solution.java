import java.util.Arrays;

public class Solution {
    public int countSubstrings(String s) {
        Integer[][] arr = new Integer[s.length()][s.length()+1];
        
        for (Integer[] row: arr) {
            Arrays.fill(row, -1);
        }

        for (int i = 0 ; i < s.length(); i++) {
        		arr[i][0] = 1;
        		arr[i][1] = 1;
        }
        
        int cnt = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
        		cnt++;
        		for (int j = i + 1; j < s.length(); j++) {
        			if (s.charAt(i) == s.charAt(j) && (j == i + 1 || arr[i+1][j-i-1] == 1)) {
        				System.out.println("(i, j) = " + i + " " + j + " " + (j-i+1));
        				System.out.println(" substring val " + arr[i+1][j-i-1]);
        				arr[i][j-i+1] = 1;
        				cnt++;
        				System.out.println(s.substring(i, j+1));
        			}
        		}
        }
        return cnt;
    }

    public static void main(String args[]) {
    		Solution s = new Solution();
    		System.out.println("count = " + s.countSubstrings("aaa"));
    		System.out.println("count = " + s.countSubstrings("dnncbwoneinoplypwgbwktmvkoimcooyiwirgbxlcttgteqthcvyoueyftiwgwwxvxvg"));
    }
}
