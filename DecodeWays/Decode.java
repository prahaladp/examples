import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Decode {
    public HashMap<Character, Integer> ciMap = new HashMap<>();
    public HashMap<Integer, Integer> indCount = new HashMap<>();
    
    public Decode() {
    }
    
    private int decodeStr(String s, int ind) {
        if (ind >= s.length()) {
            return 0;
        }
        
        if (indCount.get(ind) != null) {
            return indCount.get(ind);
        }
        
        if (ind == s.length() - 1) {
            System.out.println("ind = " + ind + " total = " + 1);
            indCount.put(ind, 1);
            return 1;
        }
        
        if (s.charAt(ind) == '1' || s.charAt(ind) == '2') {
            /* there are mutliple possibilities here */
            int opt1 = 0;
            int opt2 = 1;
            if (ind + 1 < s.length() && s.charAt(ind + 1) == '0') {
                opt1 = Math.max(1, decodeStr(s, ind + 2));
                opt2 = 0;
            } else if (s.charAt(ind) == '1') {
                opt1 = decodeStr(s, ind + 1);
                if (ind + 2 < s.length()) {
                    opt2 = 0;
                    if (s.charAt(ind + 2) != '0') {
                        opt2 = Math.max(1, decodeStr(s, ind + 2));
                    }
                } else {
                    opt2 = 1;
                }
            } else if (s.charAt(ind)  == '2') {
                opt1 = decodeStr(s, ind + 1);
                if (s.charAt(ind+1) >= '0' && s.charAt(ind+1) <= '6') {
                    opt2 = Math.max(1, decodeStr(s, ind + 2));
                } else {
                    opt2 = 0;
                }
            }
            System.out.println("ind = " + ind + " total = " + (opt1 + opt2));
            indCount.put(ind, opt1 + opt2);
            
        } else {
            System.out.println("ind = " + ind + " total = "+ decodeStr(s, ind + 1));
            indCount.put(ind, decodeStr(s, ind + 1));
        }
        
        return indCount.get(ind);
    }
    
    public int numDecodings(String s) {
        for (int i=0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '1' && c <= '9') {
                continue;
            }
            if (i != 0 &&
                (s.charAt(i-1) =='1' || s.charAt(i-1) == '2')) {
                continue;
            }
            return 0;
        }
        indCount.clear();
        return decodeStr(s, 0);
    }
    
    public static void main(String[] args) {
        Decode d = new Decode();
        //System.out.println("Number of ways = " + d.numDecodings("12"));
        System.out.println("Number of ways = " + d.numDecodings("1812"));
        System.out.println("Number of ways = " + d.numDecodings("226"));
        System.out.println("Number of ways = " + d.numDecodings("227"));
        System.out.println("Number of ways = " + d.numDecodings("110"));
    }
}
