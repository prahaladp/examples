import java.util.*;

class Graph {
  private HashMap<String, Node> nodes;
  private Map<String, Customer> customers;
  
  public Graph(HashMap<String, Customer> c) {
    nodes = new HashMap<String, Node>();
    customers = c;
  }
  
  public void addNodes(List<String> items) {
    
    for (String it : items) {
      
      Node  n = nodes.get(it);
      if (n == null) {
        n = new Node(it);
        nodes.put(it, n);
      }
    }
    computeAdj(items);
  }
  
  public void printGraph() {
    System.out.println("PrintGraph");
    Iterator  iter = nodes.entrySet().iterator();
    
    while (iter.hasNext()) {
      Map.Entry entry = (Map.Entry)iter.next();
      String    nodeName = (String)entry.getKey();
      Node      n = (Node)entry.getValue();
      n.printSummary();
    }

  }
  
  public void computeAdj(List<String> items) {
    
    for (String nodeName:items) {
      Node  n = nodes.get(nodeName);
      
      for (String adjNodes : items) {
        if (adjNodes.equals(nodeName) == false) {
          Node adjNodeEntry = nodes.get(adjNodes);
          n.addStrong(adjNodeEntry);
        }
      }
    }
  }
  
  public void build() {
    Iterator  iter = customers.entrySet().iterator();
    
    while (iter.hasNext()) {
      Map.Entry entry  = (Map.Entry)iter.next();
      String    name = (String)entry.getKey();
      Customer  cust  = (Customer)entry.getValue();

      cust.printSummary();
      List<String>  items = cust.getItems();
      addNodes(items);
    }
  }
  
  public void runBfs(String key) {    
    Queue<Node> q = new LinkedList<Node>();
    int         nodeVisited = 0;
    List<String>  strong = new ArrayList<String>();
    List<String>  weak = new ArrayList<String>();
    Node          start = (Node)nodes.get("DEF");

    printGraph();

    if (start == null) {
      System.out.println("No starting node for key " + key);
      return;
    }
    
    q.add(start);
    while (!q.isEmpty()) {
      Node        n = q.remove();
      List<Node>  adj = n.getAdj();
      
      if (n.isVisited()) {
        continue;
      }
      
      n.setVisit();
      
      for (Node a : adj) {
        q.add(a);
        if (a.isVisited()) {
          continue;
        }
        if (nodeVisited == 0) {
          strong.add(a.getName());
        } else {
          weak.add(a.getName());
        }
      }
      nodeVisited++;
    }

    printComponent("Strong Reco", strong);
    printComponent("Weak Reco", weak);
  }
  
  private void printComponent(String title, List<String> l) {
    String  s = new String();
    
    for (String lc : l) {
      s += " " + lc;
    }
    System.out.println(title + ":" + s);
  }
}