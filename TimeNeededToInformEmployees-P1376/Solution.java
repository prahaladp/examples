class Solution {
    private int searchTree(int[] manager, int[] informTime, int t) {
        List<Integer> cq = new LinkedList<>();
        for (int j = 0; j < manager.length; j++) {
            if (manager[j] == t) {
                cq.add(j);
            }
        }
        int m = 0;
        for (Integer i : cq) {
            int it = searchTree(manager, informTime, i);
            if (it > m) {
                m = it;
            }
        }
        return informTime[t] + m;
    }
    
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if (n == 0 || n == 1) {
            return 0;
        }
        return searchTree(manager, informTime, headID);
    }
}
