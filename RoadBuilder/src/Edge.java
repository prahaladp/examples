import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Edge {
  private int x;
  private int y;
  
  public Edge(int[] arr) {
    this.x = arr[0];
    this.y = arr[1];
  }

  @Override
  public String toString() {
    return "Edge [x=" + x + ", y=" + y + "]";
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getX() {
    return this.x;
  }
  
  public boolean equals(Edge e) {
    return ((e.getX() == this.x && e.getY() == this.y) ||
        (e.getX() == this.y && e.getY() == this.x));
  }
}