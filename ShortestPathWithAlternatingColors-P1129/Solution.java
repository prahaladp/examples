class Solution {

    private List<Integer> getNodes(int n, int[][] edges) {
        List<Integer> nl = new LinkedList<>();
        for (int i=0; i < edges.length; i++) {
            if (edges[i][0] == n) {
                nl.add(edges[i][1]);
            }
        }
        return nl;
    }
    
    private void computeLen(int[][] red_edges, int[][] blue_edges, Map<Integer, Integer> ans, boolean isRed) {
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Map<Boolean, List<Integer>>> viMap = new HashMap<>();
        
        q.add(0);
        int l = 1;
        
        while (q.isEmpty() == false) {    
            Queue<Integer> bfsq = new LinkedList<>();
            while (q.isEmpty() == false) {
                int n = q.poll();
                List<Integer> nn;
                if (isRed == true) {
                    nn = getNodes(n, red_edges);
                } else {
                    nn = getNodes(n, blue_edges);
                }
                Map<Boolean, List<Integer>> temp = viMap.computeIfAbsent(n, k -> new HashMap<>());
                List<Integer> vi = temp.computeIfAbsent(isRed, k -> new LinkedList<Integer>());
                System.out.println((isRed ? "Red":"Blue") + " " + n + " " + nn.toString());
                System.out.println((isRed ? "Red":"Blue") + " - " + vi.toString());
                for (Integer nnval : nn) {
                    if (vi.contains(nnval) == true) {
                        continue;
                    }

                    int v = ans.computeIfAbsent(nnval, k -> Integer.MAX_VALUE);
                    if (l < v) {
                        ans.put(nnval, l);
                    }
                    
                    vi.add(nnval);
                    bfsq.add(nnval);
                }
            }
            q = bfsq;
            l = l+1;
            isRed = (isRed == true) ? false : true;
        }
    }
    
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Map<Integer, Integer> ans = new HashMap<>();

        if (red_edges.length == 0 && blue_edges.length == 0) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = -1;
            }
            arr[0] = 0;
            return arr;
        }
        for (int i=0; i < n; i++) {
            ans.put(i, Integer.MAX_VALUE);
        }
        
        computeLen(red_edges, blue_edges, ans, true);
        computeLen(red_edges, blue_edges, ans, false);
        
        int len = ans.keySet().size();
        int[] arr = new int[len];
        
        for (Map.Entry<Integer, Integer> es : ans.entrySet()) {
            if (es.getValue() == Integer.MAX_VALUE) {
                arr[es.getKey()] = -1;
            } else {
                arr[es.getKey()] = es.getValue();
            }
        }
        arr[0] = 0;
        return arr;
    }
}
