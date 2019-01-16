import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Arrays;

class Interval {
    public int start;
    public int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
    public String toString() {
        return "Interval[" + start + "," + end + "]";
    }

}

public class RightInterval {

    class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) {
            if (i1.start < i2.start) { return -1; }
            if (i1.start == i2.start) { return 0; }
            return 1;
        }
    }

    public int[] findRightInterval(Interval[] intervals) {
        LinkedList<Interval> intV = new LinkedList<>();
        int[] modifiedIndexes = new int[intervals.length];
        int[] rightInt = new int[intervals.length];
        
        for (Interval i : intervals) {
            intV.add(i);
        }
        //System.out.println("Original List " + intV);
        
        Collections.sort(intV, new IntervalComparator());
        //System.out.println("Sorted List " + intV);
        
        for (int j = 0; j < intV.size(); j++) {
            Interval intE = intV.get(j);
            modifiedIndexes[j] = -1;
            for (int i = 0; i < intervals.length; i++) {
                Interval sInt = intervals[i];
                if (sInt.start == intE.start && sInt.end == intE.end) {
                    modifiedIndexes[j] = i;
                    break;
                }
            }
        }
        //System.out.println("ModifiedIndexes " + Arrays.toString(modifiedIndexes));
        
        for (int j = 0; j < intV.size(); j++) {
            rightInt[modifiedIndexes[j]] = -1;
            Interval lE = intV.get(j);
            for (int i = j + 1; i < intV.size(); i++) {
                Interval rE = intV.get(i);
                if (rE.start >= lE.end) {
                    // right entry of lE is rE
                    rightInt[modifiedIndexes[j]] = modifiedIndexes[i];
                    break;
                }
            }
        }
        
        System.out.println("RightIndexes " + Arrays.toString(rightInt));
        return rightInt;
    }

    public static void main(String[] args) {
        RightInterval ri = new RightInterval();
        Interval[] a = new Interval[3];
        a[0] = new Interval(3,4);
        a[1] = new Interval(1,2);
        a[2] = new Interval(2,3);
        ri.findRightInterval(a);
        
        a[0] = new Interval(3,4);
        a[1] = new Interval(2,3);
        a[2] = new Interval(1,2);
        ri.findRightInterval(a);

        a[0] = new Interval(1,4);
        a[1] = new Interval(2,3);
        a[2] = new Interval(3,4);
        ri.findRightInterval(a);
        
        Interval[] b = new Interval[1];
        b[0] = new Interval(1,2);
        ri.findRightInterval(b);

    }
}
