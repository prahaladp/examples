import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TreeDiagonalSlope {
  public TreeDiagonalSlope left;
  public TreeDiagonalSlope right;
  public int               value;
  
  public TreeDiagonalSlope(TreeDiagonalSlope l,
      TreeDiagonalSlope r, int v) {
    left = l;
    right = r;
    value = v;
  }
  
  public void diagonalTraverseNodes(Map<Integer, List<Integer>> slopeMap,
      TreeDiagonalSlope tNode, int level) {
    
    if (tNode == null) {
      return;
    }
    
    List<Integer>   lVal = slopeMap.computeIfAbsent(level, k -> new LinkedList<>());
    
    System.out.println("adding level " + level + " value : " + tNode.value);
    lVal.add(tNode.value);
    diagonalTraverseNodes(slopeMap, tNode.left, level + 1);
    diagonalTraverseNodes(slopeMap, tNode.right, level);
  }
  
  public void printSlope(Map<Integer, List<Integer>> slopeMap) {
    slopeMap.keySet().forEach(k -> {
      System.out.println("Entry(" + k + ") : " + slopeMap.get(k));
    });
  }

  public void diagonalTraverse() {
    Map<Integer, List<Integer>> slopeMap = new ConcurrentHashMap<>();
    diagonalTraverseNodes(slopeMap, this, 0);
    printSlope(slopeMap);
  }
  
  public static void main(String[] args) {
    TreeDiagonalSlope t13 = new TreeDiagonalSlope(null, null, 13);
    TreeDiagonalSlope t7 = new TreeDiagonalSlope(null, null, 7);
    TreeDiagonalSlope t4 = new TreeDiagonalSlope(null, null, 4);
    TreeDiagonalSlope t1 = new TreeDiagonalSlope(null, null, 1);
    TreeDiagonalSlope t6 = new TreeDiagonalSlope(t4, t7, 6);
    TreeDiagonalSlope t3 = new TreeDiagonalSlope(t1, null, 3);
    TreeDiagonalSlope t14 = new TreeDiagonalSlope(t13, null, 14);
    TreeDiagonalSlope t10 = new TreeDiagonalSlope(t6, t14, 10);
    TreeDiagonalSlope t8 = new TreeDiagonalSlope(t3, t10, 8);

    t8.diagonalTraverse();

  }
}
