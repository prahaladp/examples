import java.util.concurrent.ConcurrentHashMap;

public class TreeNode {
  private TreeNode  left;
  private TreeNode  right;
  private int       val;
  
  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
  
  int getVal() {
    return val;
  }
  
  public static Boolean checkValidTree(TreeNode[] tArr) {
    ConcurrentHashMap<TreeNode, Integer> tMap = new ConcurrentHashMap<>();
 
    for (int i = 0; i < tArr.length; i++) {
      TreeNode  t = tArr[i];
      Integer   count;
      
      /* account for the current node, too */
      if (tMap.get(t) == null) {
        tMap.put(t, 0);
      }
      
      if (t.left != null) {
        
        if (t.left.getVal() > t.val) {
          System.out.println("TreeNode " + t.val + " has an incorrect left child value " + t.left.getVal());
          return Boolean.FALSE;
        }
        count = tMap.get(t.left);
        
        if (count == null) {
          tMap.put(t.left, 1);
        } else if (count == 1) {
          System.out.println("TreeNode " + t.val + " : left node has multiple parents");
          return Boolean.FALSE;
        }
        
        tMap.put(t.left, count + 1);
      }
      
      if (t.right != null) {
        
        if (t.right.getVal() < t.val) {
          System.out.println("TreeNode " + t.val + " has an incorrect right child value " + t.right.getVal());
          return Boolean.FALSE;
        }

        count = tMap.get(t.right);
        
        if (count == null) {
          tMap.put(t.right, 0);
        } else if (count == 1) {
          System.out.println("TreeNode " + t.val + " : right node has multiple parents");
          return Boolean.FALSE;
        }
        
        tMap.put(t.right, count + 1);
      }    
    }
    
    return Boolean.TRUE;
  }
  
  public static void main(String[] args) {
    TreeNode[]  tArr = new TreeNode[3];
    
    /* test case 1 */
    System.out.println("TestCase 1");
    tArr[0] = new TreeNode(3, null, null);
    tArr[1] = new TreeNode(5, tArr[0], null);
    tArr[2] = new TreeNode(10, tArr[1], null);
    if (TreeNode.checkValidTree(tArr) != Boolean.TRUE) {
        System.out.println("TestCase 1 failed");
        System.exit(1);
    }
    
    System.out.println("TestCase 2");
    tArr = new TreeNode[3];
    tArr[0] = new TreeNode(3, null, null);
    tArr[1] = new TreeNode(5, tArr[0], null);
    tArr[2] = new TreeNode(10, tArr[1], tArr[0]);
    if (TreeNode.checkValidTree(tArr) != Boolean.FALSE) {
      System.out.println("TestCase 2 failed");
      System.exit(1);
    }
    
    System.out.println("TestCase 3");
    tArr = new TreeNode[4];
    tArr[0] = new TreeNode(3, null, null);
    tArr[1] = new TreeNode(5, tArr[0], null);
    tArr[3] = new TreeNode(9, null, null);
    tArr[2] = new TreeNode(10, tArr[1], tArr[3]);
    if (TreeNode.checkValidTree(tArr) != Boolean.FALSE) {
      System.out.println("TestCase 2 failed");
      System.exit(1);
    }
  }
}