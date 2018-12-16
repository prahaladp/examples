import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
  private class Fraction {
    public int i;
    public int j;
    public Float f;

    public Fraction(int i, int j) {
      this.i = i;
      this.j = j;
      this.f = (float) (i) / (float) (j);
    }
  }

  public class FractionComparator implements Comparator<Fraction> {
    @Override
    public int compare(Fraction x, Fraction y) {
      // System.out.println("Comparing " + x.f + " " + y.f);
      if (x.f < y.f) {
        return -1;
      }
      if (x.f > y.f) {
        return 1;
      }
      return 0;
    }

  }

  public int[] kthSmallestPrimeFraction(int[] A, int K) {
    int sz = A.length;
    int totalElem = sz * (sz - 1) / 2;
    FractionComparator fracComparator = new FractionComparator();

    PriorityQueue<Fraction> queue = new PriorityQueue<>(totalElem, fracComparator);
    for (int i = 0; i < A.length; i++) {
      for (int j = i + 1; j < A.length; j++) {
        Fraction f = new Fraction(A[i], A[j]);
        queue.add(f);
        // System.out.println("Priority Queue = " + queue);
      }
    }

    Fraction smallFrac = null;
    for (int i = 0; i < K; i++) {
      smallFrac = queue.remove();
      // System.out.println("Removing " + smallFrac.i + " " + smallFrac.j);
    }

    int[] fracVal = new int[2];
    if (smallFrac != null) {
      fracVal[0] = smallFrac.i;
      fracVal[1] = smallFrac.j;
    } else {
      fracVal[0] = 0;
      fracVal[0] = 0;
    }

    return fracVal;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    int[] inp = {1, 2, 3, 5};
    int[] res = s.kthSmallestPrimeFraction(inp, 3);
    System.out.println("Solution[1, 2, 3, 5] = " + res[0] + " " + res[1]);

    inp = new int[] {1, 7};
    res = s.kthSmallestPrimeFraction(inp, 1);
    System.out.println("Solution[1,7] = " + res[0] + " " + res[1]);
  }

}
