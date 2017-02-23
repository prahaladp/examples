import java.util.*;

class Node {
  private List<Node>  adj;
  private String      name;
  boolean             visit;
  
  public Node(String n) {
    name = n;
    adj = new ArrayList<Node>();
    visit = false;
  }
  
  public void addStrong(Node adjNode) {
    for (Node n : adj) {
      if (n.getName() == adjNode.getName()) {
        return;
      }
    }
    adj.add(adjNode);
  }
  
  public void printSummary() {
    String  s = name + "[";
    for (Node n : adj) {
      s = s + n.getName();
    }
    s = s + "]";
    System.out.println(s);
  }
  
  public String getName() {
    return name;
  }
  
  public List<Node> getAdj() {
    return adj;
  }
  
  public void setVisit() {
    visit = true;
  }
  
  public boolean isVisited() {
    return visit;
  }
}