import java.util.HashMap;
import java.util.PriorityQueue;

class AllOne {

    class Entry {
        private int count;
        private String key;
        public Entry(int count, String key) {
            this.count = count;
            this.key = key;
        }
        public String getKey() {
            return this.key;
        }
        public int getCount() {
            return this.count;
        }
        public void incCount() {
            this.count = this.count + 1;
        }
        public void decCount() {
            this.count = this.count - 1;
        }
    }
    private PriorityQueue<Entry> maxPq = new PriorityQueue<>((x, y) -> (y.getCount()-x.getCount()));
    private PriorityQueue<Entry> minPq = new PriorityQueue<>((x, y) -> (x.getCount()-y.getCount()));
    private HashMap<String, Entry> hashMap = new HashMap<>();

    /** Initialize your data structure here. */
    public AllOne() {
        hashMap.clear();
        maxPq.clear();
        minPq.clear();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Entry e = hashMap.get(key);
        if (e == null) {
            e = new Entry(1, key);
            hashMap.put(key, e);
        } else {
            maxPq.remove(e);
            minPq.remove(e);
            e.incCount();
        }
        maxPq.add(e);
        minPq.add(e);
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        Entry e = hashMap.get(key);
        if (e == null) {
            return;
        }
        maxPq.remove(e);
        minPq.remove(e);
        if (e.getCount() == 1) {
            hashMap.put(e.getKey(), null);
            return;
        }
        e.decCount();
        maxPq.add(e);
        minPq.add(e);
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        Entry e = maxPq.peek();
        if (e == null) {
            return null;
        }
        return e.getKey();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        Entry e = minPq.peek();
        if (e == null) {
            return null;
        }
        return e.getKey();

    }

    public static void main(String[] args) {
        AllOne obj = new AllOne();
        
        obj.inc("AAA");
        String param_3 = obj.getMaxKey();
        String param_4 = obj.getMinKey();
        System.out.println("maxkey = " + param_3);
        System.out.println("minKey = " + param_4);
        
        obj.dec("BBB");
        param_3 = obj.getMaxKey();
        param_4 = obj.getMinKey();
        System.out.println("maxkey = " + param_3);
        System.out.println("minKey = " + param_4);
        
        obj.inc("BBB");
        param_3 = obj.getMaxKey();
        param_4 = obj.getMinKey();
        System.out.println("maxkey = " + param_3);
        System.out.println("minKey = " + param_4);

        obj.dec("AAA");
        param_3 = obj.getMaxKey();
        param_4 = obj.getMinKey();
        System.out.println("maxkey = " + param_3);
        System.out.println("minKey = " + param_4);

        obj.dec("BBB");
        param_3 = obj.getMaxKey();
        param_4 = obj.getMinKey();
        System.out.println("maxkey = " + param_3);
        System.out.println("minKey = " + param_4);

    }
}

