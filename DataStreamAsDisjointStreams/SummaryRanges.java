import java.util.TreeMap;
import java.util.SortedMap;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

class SummaryRanges {
    class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) {
            start = s; end = e;
        }
        
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }
    

    HashMap<Integer, Interval> hashMap = new HashMap<>();
    SortedMap<Interval, String> treeMap = new TreeMap<>((s1, s2) -> {
        if ((s2.start >= s1.start && s2.end <= s1.end)) {
            return 0;
        }
        if ((s2.start >= s1.start && s2.start <= s1.end && s2.end >= s1.end)) {
            s1.end = s2.end;
            return 0;
        }
        if (s2.start > s1.end) {
            return -1;
        }
        
        if (s2.start < s1.start && (s2.end >= s1.start && s2.end <= s1.end)) {
            s1.start = s2.start;
            return 0;
        }
        
        if (s2.end < s1.start) {
            return 1;
        }
        return -1;
    });
    
    public SummaryRanges() {
    }
    
    public void addNum(int val) {
        Interval newI = new Interval(val, val);
        Interval existingI = hashMap.get(val);
        if (existingI != null) {
            return;
        }
        
        Interval prevI = hashMap.get(val - 1);
        Interval nextI = hashMap.get(val + 1);
        
        if (prevI == null && nextI == null) {
            treeMap.put(newI, "val");
            hashMap.put(val, newI);
            return;
        }
        
        if (prevI != null && nextI != null) {
            treeMap.remove(prevI);
            
            nextI.start = prevI.start;
            for (int i = prevI.start; i <= prevI.end; i++) {
                hashMap.put(i, nextI);
            }
            hashMap.put(val, nextI);
            return;
        }
        
        if (prevI == null && nextI != null) {
            nextI.start = val;
            hashMap.put(val, nextI);
            return;
        }
        
        if (prevI != null && nextI == null) {
            prevI.end = val;
            hashMap.put(val, prevI);
            return;
        }
    }
    
    public List<Interval> getIntervals() {
        List<Interval> l = new LinkedList<>();
        for (Interval i : treeMap.keySet()) {
            l.add(i);
        }
        return l;
    }
    
    public static void main(String[] args) {
        SummaryRanges s = new SummaryRanges();
        s.addNum(1);
        s.addNum(3);
        System.out.println("Intervals " + s.getIntervals());
        s.addNum(2);
        System.out.println("Intervals " + s.getIntervals());
        s.addNum(10);
        System.out.println("Intervals " + s.getIntervals());
        s.addNum(5);
        System.out.println("Intervals " + s.getIntervals());
        s.addNum(4);
        System.out.println("Intervals " + s.getIntervals());
    }
}
