class TreeAncestor {
    List<List<Integer>> kthArray;
    int n;
    public TreeAncestor(int n, int[] parent) {
        int maxLvl = getMaxLogLevel(n, parent);
        this.n = n;
        this.kthArray = new LinkedList<>();
        this.kthArray.add(0, new LinkedList<>());
        this.kthArray.get(0).add(0);
        for (int i = 1; i < n; i++) {
            this.kthArray.add(i, new LinkedList<>());
            this.kthArray.get(i).add(i);
            for (int j=1; j < maxLvl; j++) {
                if (parent[i] == -1
                    || this.kthArray.get(parent[i]).size() <= j-1
                    || this.kthArray.get(parent[i]).get(j-1) == -1) {
                    break;
                }
                List<Integer> cList = this.kthArray.get(i);
                cList.add(this.kthArray.get(parent[i]).get(j-1));
            } 
        }
    }
    
    private int getMaxLogLevel(int n, int[] parent) {
        int[] lvl = new int[n];
        int maxLvl = 1;
        lvl[0] = 1;
        for (int i = 1; i < n; i++) {
            lvl[i] = lvl[parent[i]] + 1;
            if (lvl[i] > maxLvl) {
                maxLvl = lvl[i];
            }
        }
        return maxLvl;
    }
    
    public int getKthAncestor(int node, int k) {
        if (node >= this.n) {
            return -1;
        }
        if (k >= this.kthArray.get(node).size()) {
            return -1;
        }
        return this.kthArray.get(node).get(k);
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
