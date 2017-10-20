import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoadBuilder {
  
  public static void main(String[] argv) {
    int[] test_v = {0, 1, 2, 3};
    int[][] test_e = new int[][] { {0,1}, {1,2}, {2,3}};
    
    List<String> un = new ArrayList<>();
    un.add("route 99.99.99.98/32");
    un.add("route 99.99.99.99/32");

    long count = un.stream().filter(l -> l.contains("99.99.99.99/32")).count();
    System.out.println("count = " + count);
        
    List<int[]> arr = Arrays.asList(test_e);
    
    List<Edge> edges = arr
      .stream()
      .map((x) -> (new Edge(x)))
      .collect(Collectors.toList());
        
    List<Vertex> vert = Arrays
      .stream(test_v)
      .boxed()
      .map((x) -> (new Vertex(x)))
      .collect(Collectors.toList());
            
    List<Edge> allEdges = vert
      .stream()
      .flatMap((v) -> (
            vert
              .stream()
              .filter((lv) -> (lv.equals(v) == false))
              .map((lv) -> (new Edge(new int[]{v.get(), lv.get()})))
          ))
      .collect(Collectors.toList());
    
    Predicate<Edge> checkEdge = (e) -> {
          List<Edge> fe = edges.stream()
            .filter((le) -> (le.equals(e)))
            .collect(Collectors.toList());
          return (fe.size() == 0);
    };
    
    List<Edge> missingEdges = allEdges
      .stream()
      .filter(checkEdge)
      .collect(Collectors.toList());
    
    System.out.println("Missing Roads");

    missingEdges
      .stream()
      .forEach(System.out::println);
  }
}