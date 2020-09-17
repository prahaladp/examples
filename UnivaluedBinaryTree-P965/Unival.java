public Node {
  private List<Node> child;
  private int val;
  
  
}

public class Tree {
  private Node root;
  
  public boolean isUnival() {
    
    if (root == null) {
      return false;
    }
    
    int rootVal = root.getVal();
    List<Node> bfsQ = new LinkedList<>();
    bfsQ.add(root);
    
    while (bfsQ.size() != 0) {
      // verify the root val is same
      Node nextNode = bfsQ.remove(0);
      if (nextNode.getVal() != rootVal) {
        return false;
      }
      
      bfsQ.add(nextNode.getChildren();
    }
    
    return true;
  }
               
  public int countUnivalTrees() {
    
    boolean allUnival = true;
    
    /*
       1
     1   1     
     */
    for (Node n : root.getChildren()) {
      Tree t = new Tree(n);
      int count = n.countUnivalTrees();
      n.setUnivalCount(count);
      
      if ( n.getUnivalFlag() == false) {
        allUnival = false;
      }
    }
        
    for (Node n : root.getChildren()) {
      if (node.getVal() != root.getVal()) {
        allUnival = false;
        break;
      }
    }
      
    sum = 0;
    for (Node n : root.getChildren()) {
        sum += n.getUnivalCount();
    }
    
    if (allUnival == true) {
      sum++; 
    }
    root.setUnivalFlag(allUnival);
    
    return sum;
  }
}
