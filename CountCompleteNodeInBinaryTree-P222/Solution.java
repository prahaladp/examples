class Solution {
    int nodeCount = 0;
    
    private void countInOrder(TreeNode root) {
        if (root == null) {
            return;
        }    
        nodeCount++;
        countInOrder(root.left);
        countInOrder(root.right);
    }
    
    public int countNodes(TreeNode root) {
        countInOrder(root);
        return nodeCount;
    }
}
