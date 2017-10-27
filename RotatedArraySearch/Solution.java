
class Solution {
    public int search(int[] nums, int target) {
        boolean go_back = false;
        int     last = 0;
        int     indx = 0;
        
        if (nums.length == 0) {
          return -1;
        }
        
        /**
         *  go front or back based on the first number
         *    if we go_front, we stop when we find the numbers decreasing
         *    if we go_back, we stop when we find the numbers increasing
         */
        last = nums[0];
        
        if (nums[0] == target) {
          return 0;
        }
        if (last > target) {
          go_back = true;
          indx = nums.length - 1;
        } else {
          indx = indx + 1;
        }
        
        while (true) {
          if (go_back == true && indx == 0) {
            return -1;
          }
          if (go_back == false && indx > nums.length - 1) {
            return -1;
          }
          if (nums[indx] == target) {
            return indx;
          }
          if (go_back == true) {
            
            if (last < nums[indx]) {
              return -1;
            }
            last = nums[indx];
            indx--;
          } else {
            if (last > nums[indx]) {
              return -1;
            }

            last = nums[indx];
            indx++;
          }
        } 
    }
    
    public static void main(String[] arg) {
      Solution  s = new Solution();
      int[]     myIntArray1 = {10, 20, 30, 1, 2};
      System.out.println("indx = " + s.search(myIntArray1, 3));
      System.out.println("indx = " + s.search(myIntArray1, 10));
      System.out.println("indx = " + s.search(myIntArray1, 2));
      System.out.println("indx = " + s.search(myIntArray1, 12));
      System.out.println("indx = " + s.search(myIntArray1, 20));
      System.out.println("indx = " + s.search(myIntArray1, 1));
      System.out.println("indx = " + s.search(myIntArray1, 30));
      System.out.println("indx = " + s.search(myIntArray1, 40));
      
      int[]     myIntArray2 = {1};
      System.out.println("indx = " + s.search(myIntArray2, 2));

      int[]     myIntArray3 = {1, 3};
      System.out.println("indx = " + s.search(myIntArray3, 3));

    }
}