import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Solution {
  
  public List<Integer> largestValues(TreeNode root) {
    List<TreeNode>  levelList = new LinkedList<>();
    List<Integer> maxIntByLevel = new LinkedList<>();
    levelList.add(root);
    
    lvEnumerate(levelList, maxIntByLevel);
    return maxIntByLevel;
  }
  
  private void lvEnumerate(List<TreeNode> lv, List<Integer> maxIntByLevel) {
    
    if (lv.size() == 0) {
      return;
    }
    
    Optional<Integer> i = lv.stream().map(entry -> entry.val).collect(Collectors.maxBy(Comparator.naturalOrder()));
    List<TreeNode> nextLvl = new LinkedList<>();
   
    for (TreeNode tr : lv) {
      if (tr.left != null) {
        nextLvl.add(tr.left);
      }
      if (tr.right != null) {
        nextLvl.add(tr.right);
      }
    }
    
    if (i.get() != null) {
      maxIntByLevel.add(i.get());
    }
    
    lvEnumerate(nextLvl, maxIntByLevel);
  }
  
  public static void main(String[] args) {
    TreeNode r = new TreeNode(1);
    TreeNode l1a = new TreeNode(3);
    TreeNode l1b= new TreeNode(2);
    TreeNode l2a = new TreeNode(5);
    TreeNode l2b = new TreeNode(3);
    TreeNode l2c = new TreeNode(9);
    
    r.left = l1a;
    r.right = l1b;
    l1a.left = l2a;
    l1a.right = l2b;
    l1b.right = l2c;
    
    Solution s= new Solution();
    System.out.println(" max by level " + s.largestValues(r));
  }
}
