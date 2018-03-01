
public class SortedOrder {
  public static boolean check(String[] params, String[] sortedOrder) {
    int ind = 0;
    int soInd = 0;
    
    for (ind = 0; ind < params.length;) {
      if (params[ind].charAt(0) == sortedOrder[soInd].charAt(0)) {
        ind++;
        continue;
      }
      
      if (soInd == (sortedOrder.length - 1)) {
        return false;
      }
      
      soInd++;
      if (params[ind].charAt(0) == sortedOrder[soInd].charAt(0)) {
        ind++;
        continue;
      }
      
      return false;
    }
    
    return true;
  }
  
  public static void main(String[] args) {
    String words[] = { "cc", "cb", "bb", "ac" };
    String order[] = { "b", "c", "a" };
    System.out.println(" result = " + SortedOrder.check(words, order));
    
    order = new String[]{ "c", "b", "a" };
    System.out.println(" result = " + SortedOrder.check(words, order));
    
    order = new String[]{ "c", "d", "b", "a" };
    System.out.println(" result = " + SortedOrder.check(words, order));


  }
}
