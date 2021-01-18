class Solution {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Queue<Pair<Integer,Boolean>> q = new LinkedList<>();
        q.add(new Pair(0, false));
        
        Map<Integer, Integer> j = new HashMap<>();
        
        for (int i=0; i < forbidden.length; i++) {
            j.put(forbidden[i], -1);
        }
                
        int moves = 0;
        while (q.isEmpty() == false) {
            Queue<Pair<Integer,Boolean>> nq = new LinkedList<>(); 
            System.out.println(".....");
            while (q.isEmpty() == false) {
                Pair<Integer,Boolean> p = q.remove();
                int s = p.getKey();
                // System.out.println(s);
                if (s == x) {
                    return moves;
                }
                if(j.get(s) != null) {
                    continue;
                }
                
                j.put(s,0);
                
                int left = s - b;
                int right = s + a;
                
                if(!p.getValue() && left >= 0 && j.get(left) == null) {  
                    nq.add(new Pair(left, true));
                }
                
                if(j.get(right)==null && right <= 3000 + 2 * b) {
                    nq.add(new Pair(right, false));
                }
            }
            moves++;
            q=nq;
        }
        return -1;
    }
}
