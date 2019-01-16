import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;


public class MatchSquare {
    private int sideLen(int[] nums) {
        int total = 0;
        for (int i = 0; i < nums.length; total += nums[i], i++);
        return total;
    }
    
    public boolean makesquare(int[] nums) {
        int totalLen = sideLen(nums);
        
        if (totalLen % 4 != 0) {
            return false;
        }
        
        int sqLen = sideLen(nums) / 4;
        
        Arrays.sort(nums);
        
        Integer[] newArray = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            newArray[i] = nums[i];
        }

        List<Integer> sortedList = new LinkedList<Integer>(Arrays.asList(newArray));
        
        return makeSq(sortedList, 0, 0, sqLen);
    }
    
    private boolean makeSq(List<Integer> sL, int nSide, int curLen, int sqLen) {
        int n = sL.size() - 1;
        System.out.println("Current List = " + sL + " Size = " + sL.size() + " curLen = " + curLen + " sqLen = " + sqLen);

        if (sL.size() == 0) {
            return true;
        }
        
        while (n >= 0) {
            int nextLen = sL.get(n);
            int newCurLen = curLen + nextLen;
            if (newCurLen < sqLen) {
                // check if we can make progress
                sL.remove(n);
                if (makeSq(sL, nSide, curLen + nextLen, sqLen) == true) {
                    return true;
                }
                
                sL.add(n, nextLen);
            } else if (newCurLen == sqLen) {
                if (nSide == 4) {
                    if (sL.size() == 1) {
                        return true;
                    }
                    return false;
                }
                nSide++;
                sL.remove(n);
                if (makeSq(sL, nSide, 0, sqLen) == true) {
                    return true;
                }
                sL.add(n, nextLen);
                nSide--;
            }
            
            n--;
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        MatchSquare s = new MatchSquare();
        int[] nums = {1,1,2,2,2};
        System.out.println("Result = " + s.makesquare(new int[]{1,1,2,2,2}));
        System.out.println("Result = " + s.makesquare(new int[]{3,3,3,3,4}));
        System.out.println("Result = " + s.makesquare(new int[]{5,5,5,5,16,4,4,4,4,4,3,3,3,3,4}));

    }
}
