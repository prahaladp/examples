class MinStack {
    List<Integer> s;
    int minnum = Integer.MAX_VALUE;
    
    /** initialize your data structure here. */
    public MinStack() {
        s = new LinkedList<>();
    }
    
    public void push(int x) {
        s.add(x);
        if (x < minnum) {
            minnum = x;
        }
    }
    
    public void findMin() {
        minnum = Integer.MAX_VALUE;
        for (Integer i : s) {
            if (i < minnum) {
                minnum = i;
            }
        }
    }
    
    public void pop() {
        if (s.size() == 0) {
            return;
        }
        int n = s.get(s.size() - 1);
        s.remove(s.size() - 1);
        if (n == minnum) {
            findMin();
        }
    }
    
    public int top() {
        if (s.size() == 0) {
            return 0;
        }
        return s.get(s.size() - 1);
    }
    
    public int getMin() {
        if (s.size() == 0) {
            return 0;
        }
        return minnum;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
