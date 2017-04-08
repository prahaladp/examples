import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class TreeNode {
  private Optional<TreeNode>  left;
  private Optional<TreeNode>  right;
  private int       val;
  
  public TreeNode(int val, Optional<TreeNode> left, Optional<TreeNode> right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
  
  int getVal() {
    return val;
  }
  
  public static Boolean checkValidTreeWithStream(TreeNode[] tArr) {
    
    
    Stream<TreeNode> tN =  Arrays.asList(tArr)
      .stream()
      .map(entry -> entry.left.get());
    


    return tN.equals(tArr[0]);
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
      
      if (t.left.isPresent()) {
        
        if (t.left.get().getVal() > t.val) {
          System.out.println("TreeNode " + t.val + " has an incorrect left child value " + t.left.get().getVal());
          return Boolean.FALSE;
        }
        count = tMap.get(t.left.get());
        
        if (count == null) {
          tMap.put(t.left.get(), 1);
        } else if (count == 1) {
          System.out.println("TreeNode " + t.val + " : left node has multiple parents");
          return Boolean.FALSE;
        }
        
        tMap.put(t.left.get(), count + 1);
      }
      
      if (t.right.isPresent()) {
        
        if (t.right.get().getVal() < t.val) {
          System.out.println("TreeNode " + t.val + " has an incorrect right child value " + t.right.get().getVal());
          return Boolean.FALSE;
        }

        count = tMap.get(t.right.get());
        
        if (count == null) {
          tMap.put(t.right.get(), 0);
        } else if (count == 1) {
          System.out.println("TreeNode " + t.val + " : right node has multiple parents");
          return Boolean.FALSE;
        }
        
        tMap.put(t.right.get(), count + 1);
      }    
    }
    
    int rootCount = 0;
    for (TreeNode key : tMap.keySet()) {
      Integer value = tMap.get(key);
      if (value == 0) {
        if (rootCount != 0) {
          System.out.println("Multiple roots found for this tree node set");
          return Boolean.FALSE;
        }
        rootCount = 1;
      }
  }
    
    return Boolean.TRUE;
  }
  
  public static void main(String[] args) {
    TreeNode[]  tArr = new TreeNode[3];
    
    /* test case 1 */
    System.out.println("TestCase 1");
    tArr[0] = new TreeNode(3, Optional.empty(), Optional.empty());
    tArr[1] = new TreeNode(5, Optional.of(tArr[0]), Optional.empty());
    tArr[2] = new TreeNode(10, Optional.of(tArr[1]), Optional.empty());
    if (TreeNode.checkValidTree(tArr) != Boolean.TRUE) {
        System.out.println("TestCase 1 failed");
        System.exit(1);
    }
    
    System.out.println("TestCase 2");
    tArr = new TreeNode[3];
    tArr[0] = new TreeNode(3, Optional.empty(), Optional.empty());
    tArr[1] = new TreeNode(5, Optional.of(tArr[0]), Optional.empty());
    tArr[2] = new TreeNode(10, Optional.of(tArr[1]), Optional.of(tArr[0]));
    if (TreeNode.checkValidTree(tArr) != Boolean.FALSE) {
      System.out.println("TestCase 2 failed");
      System.exit(1);
    }
    
    System.out.println("TestCase 3");
    tArr = new TreeNode[4];
    tArr[0] = new TreeNode(3, Optional.empty(), Optional.empty());
    tArr[1] = new TreeNode(5, Optional.of(tArr[0]), Optional.empty());
    tArr[3] = new TreeNode(9, Optional.empty(), Optional.empty());
    tArr[2] = new TreeNode(10, Optional.of(tArr[1]), Optional.of(tArr[3]));
    if (TreeNode.checkValidTree(tArr) != Boolean.FALSE) {
      System.out.println("TestCase 3 failed");
      System.exit(1);
    }
    
    System.out.println("TestCase 4");
    tArr = new TreeNode[4];
    tArr[0] = new TreeNode(3, Optional.empty(), Optional.empty());
    tArr[1] = new TreeNode(5, Optional.of(tArr[0]), Optional.empty());
    tArr[2] = new TreeNode(10, Optional.of(tArr[1]), Optional.empty());
    tArr[3] = new TreeNode(15, Optional.empty(), Optional.empty());
    if (TreeNode.checkValidTree(tArr) != Boolean.FALSE) {
      System.out.println("TestCase 4 failed");
      System.exit(1);
    }
   
    tArr[0] = new TreeNode(3, Optional.empty(), Optional.empty());
    tArr[1] = new TreeNode(5, Optional.of(tArr[0]), Optional.empty());
    tArr[2] = new TreeNode(10, Optional.of(tArr[1]), Optional.empty());
    tArr[3] = new TreeNode(15, Optional.empty(), Optional.empty());

    TreeNode.checkValidTreeWithStream(tArr);
  }
}