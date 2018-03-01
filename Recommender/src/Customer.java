import java.util.*;

class Customer {
  private String        name;
  private List<String> items;
  
  public Customer(String n) {
    name = n;
    items = new ArrayList<String>();
  }
  
  public void addItem(String item) {
    items.add(item);
  }
  
  public List<String> getItems() {
    return items;
  }
  
  public void printSummary() {
    System.out.println(name + ":" + items);
    return;
  }
}