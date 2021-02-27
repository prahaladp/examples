class Solution {
    
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < edges.length; i++) {
            matrix[edges[i][0]][edges[i][1]] = edges[i][2];
            matrix[edges[i][1]][edges[i][0]] = edges[i][2];
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0 && i!=j)
                    matrix[i][j]=99999;
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        int[] counts = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] <= distanceThreshold && matrix[i][j]!=0)
                    counts[i]++;
            }
        }
        int min = Integer.MAX_VALUE;
        int pos = -1;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] <= min) {
                min = counts[i];
                pos = i;
            }
        }
        return pos;
     }
}
