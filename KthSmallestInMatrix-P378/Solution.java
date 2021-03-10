class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                pq.add(matrix[i][j]);
            }
        }
        int num = 0;
        while (k-- != 0) {
            num = pq.remove();
        }
        return num;
    }
}
