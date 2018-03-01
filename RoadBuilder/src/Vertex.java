import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex {
  private int v;
  
  public Vertex(int v) {
    this.v = v;
  }
  
  public boolean equal(Vertex v) {
    return v.get() == this.v;
  }
  
  public int get() {
    return this.v;
  }

  @Override
  public String toString() {
    return "Vertex [v=" + this.v + "]";
  }
}