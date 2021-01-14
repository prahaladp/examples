class Solution {
    public void inOrder(Map<Integer, List<Integer>> mp, TreeNode root, int lvl) {
        if (root == null) {
            return;
        }
        
        if (root.left == null && root.right == null) {
            List<Integer> l = mp.computeIfAbsent(lvl, (k) -> { return new ArrayList<>();});
            l.add(root.val);
            return;
        }
        
        inOrder(mp, root.left, lvl+1);
        inOrder(mp, root.right, lvl+1);
    }
    
    public int deepestLeavesSum(TreeNode root) {
        Map<Integer, List<Integer>> level = new HashMap<>();
        
        inOrder(level, root, 1);
        
        int m = -1;
        for (Integer i : level.keySet()) {
            if (i > m) {
                m = i;
            }
        }
        
        if (m == -1) {
            return 0;
        }
        return level.get(m).stream().mapToInt(Integer::intValue).sum();
    }
}
