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
    
    public BSTIterator(TreeNode root) {
        this.root = root;
        this.treeNodeList = new LinkedList<>();
        inOrder(this.treeNodeList, this.root);
    }
    
    private void inOrder(List<TreeNode> lt, TreeNode curr) {
        if (curr == null) {
            return;
        }
        inOrder(lt, curr.left);
        lt.add(curr);
    }
    
    /** @return the next smallest number */
    public int next() {
        int retVal = treeNodeList.get(0).val;
        TreeNode curr = treeNodeList.get(0);
        treeNodeList.remove(0);
        
        if (curr.right != null) {
            List<TreeNode> nlt = new LinkedList<>();
            inOrder(nlt, curr.right);
            nlt.addAll(this.treeNodeList);
            this.treeNodeList = nlt;
        }
        return retVal;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (treeNodeList.size() > 0) {
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
