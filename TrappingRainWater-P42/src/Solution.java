
/**
 * https://leetcode.com/problems/trapping-rain-water/description/
 * @author Prashanth
 */
class Solution {
    public int trap(int[] height) {
      int minL;
      int trapL = 0;
      int i = 0;
      
      for (i = 0; i < height.length; i++) {
        if (height[i] != 0) {
          break;
        }
      }
      if (i >= height.length) {
        return 0;
      }
      
      int index = i;
      minL = height[i];
      int accuSum = 0;
      for (i = i + 1; i < height.length; i++) {
        if (height[i] < minL) {
          trapL += (minL - height[i]);
          continue;
        }
        accuSum += trapL;
        trapL = 0;
        minL = height[i];
        index = i;
      }
      for (i = height.length - 1; i >= index; i--) {
        if (height[i] != 0) {
          break;
        }
      }
      trapL = 0;
      minL = height[i];
      for (i = i - 1; i >= index; i--) {
        if (height[i] < minL) {
          trapL += (minL - height[i]);
          continue;
        }
        accuSum += trapL;
        trapL = 0;
        minL = height[i];
      }

      return accuSum;
    }
    
    public static void main(String[] argv) {
      Solution s = new Solution();
      int[] h = {0,1,0,2,1,0,1,3,2,1,2,1};
      System.out.println("Trap = " + s.trap(h));
      
      h = new int[] {4, 2, 3};
      System.out.println("Trap = " + s.trap(h));

    }
}