class Solution {
    public int minOperations(int n) {
        int[] arr = new int[n];
        int sum = 0;
        
        for (int i = 0; i < n; i++) {
            arr[i] = (2 * i) + 1;
            sum += arr[i];
        }
        
        int num = sum/n;
        int op = 0;
        for (int l = 0, r = n-1; l<r; l++, r--) {
            op += arr[r] - num;
        }
        return op;
    }
}
