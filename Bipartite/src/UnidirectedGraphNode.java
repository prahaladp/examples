import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnidirectedGraphNode {
        int label;
    List<UnidirectedGraphNode> neighbors;
    enum group_type {
      UNKNOWN,
      L1,
      L2,
    };
    group_type grp;

    UnidirectedGraphNode(int x) { 
       label = x; 
       grp = group_type.UNKNOWN;
       neighbors = new ArrayList<>(); 
    }
    
    public void set_neighbours(List<UnidirectedGraphNode> sn) {
      neighbors = sn;
    }
    
    private void set_group(group_type gr) {
      this.grp = gr;
    }
    
    private group_type get_group() {
      return this.grp;
    }
    
    private Boolean mark_neighbour(UnidirectedGraphNode neigh) {
      group_type  other_grp = (grp == group_type.L1) ? group_type.L2 : group_type.L1;
      if (neigh.get_group() == group_type.UNKNOWN) {
        neigh.set_group(other_grp);
        return Boolean.TRUE;
      }
      if (neigh.get_group() == other_grp) {
        return Boolean.TRUE;
      }
      
      System.out.println("Node " + neigh + " already marked with " + neigh.get_group());
      return Boolean.FALSE;
    }
    
    private List<UnidirectedGraphNode> get_neighbours() {
      return neighbors;
    }
    
    public Boolean mark_groups(List<UnidirectedGraphNode> nodes) {
      for (UnidirectedGraphNode n : nodes) {
        if (n.get_group() == group_type.UNKNOWN) {
          n.set_group(group_type.L1);
        }
        
        for (UnidirectedGraphNode nt : n.get_neighbours())
          if (n.mark_neighbour(nt) == Boolean.FALSE) {
            System.out.println("Unable to mark neighbour of node " + n + " neighbour = " + nt);
            return Boolean.FALSE;
          }
      }
      return Boolean.TRUE; 
    }
    
    public Boolean check_connectivity(List<UnidirectedGraphNode> nodes) {
      Set<UnidirectedGraphNode> l1_nodes = new HashSet<UnidirectedGraphNode>();
      Set<UnidirectedGraphNode> l2_nodes = new HashSet<UnidirectedGraphNode>();
      
      for (UnidirectedGraphNode n : nodes) {
        if (n.get_group() == group_type.L1) {
          l1_nodes.add(n);
        } else {
          l2_nodes.add(n);
        }
      }
      
      System.out.println("L1 = " + l1_nodes);
      System.out.println("L2 = " + l2_nodes);
      
      for (UnidirectedGraphNode n : nodes) {
        System.out.println("Node neighbours = " + n.get_neighbours());
        if (n.get_group() == group_type.L1) {
          if (n.get_neighbours().size() == l2_nodes.size() &&
              l2_nodes.containsAll(n.get_neighbours()) == true) {
            continue;
          }
          System.out.println("Node : " + n + " does not have all neighbours in L2 set " +
                l2_nodes);
          return Boolean.FALSE;
        } else {
          if (n.get_neighbours().size() == l1_nodes.size() &&
              l1_nodes.containsAll(n.get_neighbours()) == true) {
            continue;
          }
          System.out.println("Node : " + n + " does not have all neighbours in L2 set " +
                l1_nodes);
          return Boolean.FALSE;
        }
      }
      return Boolean.TRUE;
    }

    public boolean isBipartite(List<UnidirectedGraphNode> nodes){
      if (mark_groups(nodes) == Boolean.FALSE) {
        System.out.println("Unable to mark groups ");
        return Boolean.FALSE;
      }
      
      return check_connectivity(nodes);
    }
    
    public String toString() {
      String node = "Node = " + label;
      return node;
    }
    
    public static void TestCase1() {
      List<UnidirectedGraphNode> all_nodes = new ArrayList<UnidirectedGraphNode>();
      UnidirectedGraphNode       n1 = new UnidirectedGraphNode(1);
      UnidirectedGraphNode       n2 = new UnidirectedGraphNode(2);
      UnidirectedGraphNode       n3 = new UnidirectedGraphNode(3);
      all_nodes.add(n1);
      all_nodes.add(n2);
      all_nodes.add(n3);
      
      n1.set_neighbours(Arrays.asList(n2, n3));
      
      System.out.println("testcase 1 : " + n1.isBipartite(all_nodes));
    }
    
    public static void TestCase2() {
      List<UnidirectedGraphNode> all_nodes = new ArrayList<UnidirectedGraphNode>();
      UnidirectedGraphNode       n1 = new UnidirectedGraphNode(1);
      UnidirectedGraphNode       n2 = new UnidirectedGraphNode(2);
      UnidirectedGraphNode       n3 = new UnidirectedGraphNode(3);
      UnidirectedGraphNode       n4 = new UnidirectedGraphNode(4);
      all_nodes.add(n1);
      all_nodes.add(n2);
      all_nodes.add(n3);
      all_nodes.add(n4);
      
      n1.set_neighbours(Arrays.asList(n2, n3));
      n2.set_neighbours(Arrays.asList(n4));
      
      System.out.println("testcase 2 : " + n1.isBipartite(all_nodes));
    }
    
    public static void TestCase3() {
      List<UnidirectedGraphNode> all_nodes = new ArrayList<UnidirectedGraphNode>();
      UnidirectedGraphNode       n1 = new UnidirectedGraphNode(1);
      UnidirectedGraphNode       n2 = new UnidirectedGraphNode(2);
      UnidirectedGraphNode       n3 = new UnidirectedGraphNode(3);
      UnidirectedGraphNode       n4 = new UnidirectedGraphNode(4);
      all_nodes.add(n1);
      all_nodes.add(n2);
      all_nodes.add(n3);
      all_nodes.add(n4);
      
      n1.set_neighbours(Arrays.asList(n2, n3));
      n2.set_neighbours(Arrays.asList(n4, n1));
      n3.set_neighbours(Arrays.asList(n1, n4));
      n4.set_neighbours(Arrays.asList(n2, n3));
      
      System.out.println("testcase 3 : " + n1.isBipartite(all_nodes));
      
    }

    public static void TestCase4() {
      List<UnidirectedGraphNode> all_nodes = new ArrayList<UnidirectedGraphNode>();
      UnidirectedGraphNode       n1 = new UnidirectedGraphNode(1);
      UnidirectedGraphNode       n2 = new UnidirectedGraphNode(2);
      UnidirectedGraphNode       n3 = new UnidirectedGraphNode(3);
      UnidirectedGraphNode       n4 = new UnidirectedGraphNode(4);
      all_nodes.add(n1);
      all_nodes.add(n2);
      all_nodes.add(n3);
      all_nodes.add(n4);
      
      n1.set_neighbours(Arrays.asList(n2, n3));
      n2.set_neighbours(Arrays.asList(n4, n1));
      n3.set_neighbours(Arrays.asList(n1, n4));
      n4.set_neighbours(Arrays.asList(n2, n3, n1));
      
      System.out.println("testcase 4 : " + n1.isBipartite(all_nodes));
      
    }

    public static void main(String[] arg) {
      UnidirectedGraphNode.TestCase1();
      UnidirectedGraphNode.TestCase2();
      UnidirectedGraphNode.TestCase3();
      UnidirectedGraphNode.TestCase4();
    }
}
