class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }
        int[] counts = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            counts[tasks[i] - 'A']++;
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> (b-a));
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                pq.add(counts[i]);
            }
        }
        
        int cycles = 0;
        while (pq.isEmpty() == false) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i<n+1; i++) {
                if (pq.isEmpty() == false) {
                    temp.add(pq.remove());
                }
            }
            for (int i : temp) {
                if (i!=1) {
                    pq.add(i-1);
                }
            }
            cycles += (pq.isEmpty() == true) ? temp.size() : n+1;
        }
        
        return cycles;
    }
}
