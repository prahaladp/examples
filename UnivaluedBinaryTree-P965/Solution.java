class Solution {
  public boolean isUnivalTree(TreeNode root) {
    
    if (root == null) {
      return false;
    }
    
    int rootVal = root.val;
    List<TreeNode> bfsQ = new LinkedList<>();
    bfsQ.add(root);
    
    while (bfsQ.size() != 0) {
      // verify the root val is same
      TreeNode nextNode = bfsQ.remove(0);
      if (nextNode.val != rootVal) {
        return false;
      }
      if (nextNode.left != null) {
          bfsQ.add(nextNode.left);
      }
      if (nextNode.right != null) {
          bfsQ.add(nextNode.right);
      }  
    }
    
    return true;
  }       
}
