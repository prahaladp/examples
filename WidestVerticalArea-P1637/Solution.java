class Solution {
    public int maxWidthOfVerticalArea(int[][] arr) {
        Arrays.sort(arr, (a,b)->a[0]-b[0]);
        int m = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i][0] - arr[i-1][0] > m) {
                m = arr[i][0] - arr[i-1][0];
            }
        }
        return m;
    }
}
