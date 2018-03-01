import java.util.*;

class Recommender {
  private String              searchItem;
  HashMap<String, Customer>   customerList;
  
  public Recommender(String search, HashMap<String, Customer> customer) {
    searchItem = search;
    customerList = customer;
  }
  
  public void find() {
    Graph g = new Graph(customerList);
    g.build();
    g.runBfs(searchItem);
  }
  
  public static void main(String[] args) {
    Config      c = new Config(args[0]);
    c.parse();
    
    Recommender r = new Recommender(args[1], c.getCustomers());
    r.find(); 
  }
}