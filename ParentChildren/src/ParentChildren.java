import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ParentChildren {
  List<String> rS;
  Map<Integer, List<Node>> childMap;
  Map<Integer, Integer> parentMap;

  class Node {
    int id;
    int pid;
    String name;
    
    public Node(String nodeStr) {
      String[] tok = nodeStr.split(",");
      id = Integer.parseInt(tok[0].trim());
      pid = Integer.parseInt(tok[1].trim());
      name = tok[2];
    }
    
    public int getId() {
      return id;
    }
    
    public int getPid() {
      return pid;
    }
    
    public String getName() {
      return name;
    }
  }
  
  public void parseRelation() {
    rS.stream().forEach(rsEntry -> {
        Node n = new Node(rsEntry);
        List<Node> childEntry = childMap.computeIfAbsent(n.getPid(), val -> new LinkedList<>());
        childEntry.add(n);
        
        parentMap.put(n.getId(), n.getPid());
        System.out.println("current node " + n.getId() + "," + n.getPid());
      }
    );
  }
  
  public int getRoot() throws Exception {
    Set<Integer> nodeWithChildren = childMap.keySet().stream().collect(Collectors.toSet());
    Set<Integer> nodeWithParent = parentMap.keySet().stream().collect(Collectors.toSet());
    
    System.out.println("nodeWithChildren " + nodeWithChildren);
    System.out.println("nodeWithParent " + nodeWithParent);
    Optional<Integer> rootEntry = nodeWithChildren
        .stream()
        .filter(id -> nodeWithParent.contains(id) == false)
        .findFirst();
    
    if (rootEntry.isPresent()) {
      return rootEntry.get();
    }   

    throw new Exception("No root found");
  }
  
  public Set<Integer> getChildren(int id) {
    Set<Integer> backLog = new HashSet<>();
    Set<Integer> children = new HashSet<>();
    
    backLog.add(id);
    
    while (backLog.isEmpty() == false) {
      Optional<Integer> currId = backLog.stream().findFirst();
      List<Node> cList = childMap.get(currId.get());
      
      backLog.remove(currId.get());
      if (cList != null && cList.isEmpty() == false) {
        cList.stream().forEach(entry -> {
          backLog.add(entry.getId());
          children.add(entry.getId());
        });
      }
    }
    
    return children;
  }
  
  public ParentChildren(List<String> relationShip) {
    this.rS = relationShip;
    childMap = new ConcurrentHashMap<>();
    parentMap = new ConcurrentHashMap<>();
    parseRelation();
  }
  
  public static void main(String[] args) {
    List<String> rs = Arrays.asList(
        "10,1,Comedy Books", 
        "20, 2, Tablets",
        "1, -1, Books" ,
        "11, 1, Novels", 
        "12, 11, Terror novels", 
        "2, -1, Electronics",
        "-1, 0, GlobalRoot"
        );
    ParentChildren pc = new ParentChildren(rs);
    try {
      System.out.println("root = " + pc.getRoot());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      System.out.println(e.getMessage());
    }
    System.out.println("children(2) = " + pc.getChildren(2));
    System.out.println("children(-1) = " + pc.getChildren(-1));
    System.out.println("children(0) = " + pc.getChildren(0));


  }

}
