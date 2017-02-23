import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

class Config {
  private String fileName;
  private HashMap<String, Customer> customers;
  
  public Config(String fn) {
    fileName = fn;
    customers = new HashMap<String, Customer>();
  }
  
  public void parse() {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      
      while ((line = br.readLine()) != null) {
        String parts[] = line.split(":");
        String customerName = parts[0];
        String item = parts[1].trim();
        
        Customer nCust = customers.get(customerName);
        if (nCust == null) {
          nCust = new Customer(customerName);
          customers.put(customerName, nCust);
        }
        nCust.addItem(item);
        nCust.printSummary();
      }
    } catch (Exception e) {
      System.out.println("Exception reading file " + fileName);
    }
  }
  
  public HashMap<String, Customer> getCustomers() {
    return customers;
  }
  
}