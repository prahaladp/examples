class Solution {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> cList = new LinkedList<>();
        if (root == null) {
            return true;
        }
        cList.add(root.left);
        cList.add(root.right);
        
        while (cList.size() != 0) {
            TreeNode l = cList.poll();
            TreeNode r = cList.poll();
            
            if (l == null && r == null) {
                continue;
            }
            if (l == null ^ r == null) {
                return false;
            }
            if (l.val != r.val) {
                return false;
            }
            
            cList.add(l.left);
            cList.add(r.right);
            cList.add(l.right);
            cList.add(r.left);
        }
        
        return true;
    }
}
