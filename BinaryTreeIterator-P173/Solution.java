/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class BSTIterator {
    TreeNode root;
    List<TreeNode> treeNodeList;
    int index = 0;
    
    public BSTIterator(TreeNode root) {
        this.root = root;
        this.treeNodeList = new LinkedList<>();
        inOrder(this.root);
    }
    
    private void inOrder(TreeNode curr) {
        if (curr == null) {
            return;
        }
        inOrder(curr.left);
        treeNodeList.add(curr);
        inOrder(curr.right);
    }
    
    /** @return the next smallest number */
    public int next() {
        int retVal = treeNodeList.get(this.index).val;
        this.index++;
        return retVal;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (this.index < treeNodeList.size()) {
            return true;
        }
        return false;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
