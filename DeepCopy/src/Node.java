import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Node
{
  public int Value;
  public Node Next;
  public Node Random;
  public int  Tab;
  
  public Node(int val) {
    Value = val;
    Next = null;
    Random = null;
    Tab = 0;
  }
  
  public Node(Node src) {
    Value = src.getValue();
    
    if (src.getNext() != null) {
      Next = new Node(src.getNext());
    }
    
    if (src.getRandom() != null) {
      Random = new Node(src.getRandom());
    }
    
    Tab = src.getTab();
  }
  
  private Node(NodeBuilder nb) {
    Value = nb.Value;
    Next = nb.Next;
    Random = nb.Random;
    Tab = nb.Tab;
  }
  
  public int getValue() {
    return Value;
  }
  
  public Node getNext() {
    return Next;
  }
  
  public Node getRandom() {
    return Random;
  }
  
  public String getSpaces(int tab) {
    return String.join("", Collections.nCopies(tab * 2, " "));
  }
  
  public void setTab(int tab) {
    Tab = tab;
  }
  
  public int getTab() {
    return Tab;
  }
  
  public String toString() {
    
    String str = "";
    str = str.concat(getSpaces(Tab) + "Value : " + Value + " Tab : " + Tab + "\n");
    
    if (Next != null) {
      str = str.concat(getSpaces(Tab) + "Next : {\n");
      Next.setTab(Tab + 1);
      str = str.concat(Next.toString());
    }
    
    if (Random != null) {
      str = str.concat(getSpaces(Tab) + "Random : {\n");
      Random.setTab(Tab + 1);
      str = str.concat(Random.toString());
    }
    
    str = str.concat(getSpaces(Tab == 0 ? 0 : Tab - 1) + "}\n");
    return str;
  }
  
  public static class NodeBuilder {
    int   Tab;
    int   Value;
    Node  Next;
    Node  Random;
    
    public NodeBuilder(int iValue) {
      Value = iValue;
      Next = null;
      Random = null;
      Tab = 0;
    }
    
    public NodeBuilder setTab(int tab) {
      Tab = tab;
      return this;
    }
    
    public NodeBuilder setNext(Node next) {
      Next = next;
      return this;
    }
    
    public NodeBuilder setRandom(Node random) {
      Random = random;
      return this;
    }
    
    public Node build() {
      return new Node(this);
    }
  }
  
  public static void testcase1() {
    Node r2 = new Node.NodeBuilder(110)
        .build();
    Node n2 = new Node.NodeBuilder(11)
        .setRandom(r2)
        .build();
    Node r1 = new Node.NodeBuilder(101)
        .build();
    Node n1 = new Node.NodeBuilder(10)
        .setNext(n2)
        .setRandom(r1)
        .setTab(1)
        .build();
 
    Node dc1 = new Node(n1);
    
    String n1Str = n1.toString();
    String dc1Str = dc1.toString();
    
    System.out.println("N1 = \n" + n1Str);
    
    System.out.println("DC1 = \n" +  dc1Str);
    
    assert(n1Str == dc1Str);
  }
  
  public static void main(String[] args) {
    // Node.testcase1();
    List<String> cList = Arrays.asList("a", "b", "c");
    List<String> eList = Arrays.asList("e", "a");
    
    System.out.println("Contains : " + eList.contains("a"));
    System.out.println("Contains : " + eList.contains("e"));
    cList.stream().filter(cName -> eList.contains(cName) == false).forEach(cName -> System.out.println("cname " + cName));
    
  }
}
