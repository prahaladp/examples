class Solution {
    public int[] sortedSquares(int[] A) {
        int psIndex = 0;
        for (int i=0; i < A.length;i++) {
            if (A[i] >= 0) {
                psIndex = i;
                break;
            }
        }
        
        int i = psIndex;
        int j = psIndex-1;
        int[] sqArr = new int[A.length];
        int ci = 0;
        while (i < A.length || j >= 0) {
            if (i >= A.length && j >= 0) {
                sqArr[ci++] = A[j] * A[j];
                j--;
                continue;
            }
            if (i < A.length && j <0) {
                sqArr[ci++] = A[i] * A[i];
                i++;
                continue;
            }
            if (A[i] < Math.abs(A[j])) {
                sqArr[ci++] = A[i] * A[i];
                i++;
            } else {
                sqArr[ci++] = A[j] * A[j];
                j--;
            }
        }
        
        return sqArr;
    }
}
