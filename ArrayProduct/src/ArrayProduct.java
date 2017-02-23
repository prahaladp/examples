import java.util.*;
import java.io.*;
import java.util.stream.*;

class ArrayProduct {
  
  public List<Integer> variation1(String[] args) {
    List<String> strList = Arrays.asList(args);
    List<Integer> numList = new ArrayList<Integer>();
    List<Integer> finalResult = new ArrayList<Integer>();
    Integer       prod = 1;
    
    numList = strList.stream()
      .map(s -> {
        return Integer.parseInt(s);
      })
      .collect(Collectors.toList());
    
    
    prod = numList.stream()
        .reduce(1, (p1, p2) -> p1 * p2);
    
    System.out.println("Product is " + prod);

    for (Integer n : numList) {
      finalResult.add(prod/n);
    }
    System.out.println(finalResult);    
    return finalResult;
  }
  
  public List<Integer> variation2(String[] args) {
    Integer prod = 1;
    List<Integer> finalResult = new ArrayList<Integer>();
    
    for (String s : args) {
      prod *= Integer.parseInt(s);
    }
    
    for (String s : args) {
      finalResult.add(prod/Integer.parseInt(s));
    }
    return finalResult;
  }
  
  public void print(List<Integer> prod) {
    System.out.println("Final Result");
    for (Integer i : prod) {
        System.out.println(i);
    }
  }
  public static void main(String[] args) {
    ArrayProduct  ap = new ArrayProduct();
    
    ap.print(ap.variation1(args));
    ap.print(ap.variation2(args));
  }
}