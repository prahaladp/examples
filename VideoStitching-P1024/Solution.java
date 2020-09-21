class Solution {
    public void dfsSearch(int[][] clips, Map<Integer, List<Integer>> map, int T,
                         int ind, Map<Integer, Integer> minLenMap) {
        if (minLenMap.get(ind) != null) {
            return;
        }
        List<Integer> clipList = map.get(ind);
        if (clipList == null) {
            return;
        }
        int minLen = Integer.MAX_VALUE;
        for(Integer c : clipList) {
            // System.out.println("Clips : " + clips[c][0] + " len " + clips[c][1]);
            if (clips[c][1] >= T) {
                minLenMap.put(clips[c][0], 1);
                // System.out.println("Inserting " + clips[c][0] + " " + 1);
                return;
            }
            for (int i = clips[c][0]+1; i <= clips[c][1]; i++) {
                if (minLenMap.get(i) == null) {
                    dfsSearch(clips, map, T, i, minLenMap);
                }
                if (minLenMap.get(i) == null) {
                    continue;
                }
                if (1 + minLenMap.get(i) < minLen) {
                    minLen = 1 + minLenMap.get(i);
                    // System.out.println("Inserting " + ind + " " + minLen);
                    minLenMap.put(ind, 1 + minLenMap.get(i));
                }
           }
        };
    }
    
    public int videoStitching(int[][] clips, int T) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> res = new HashMap<>();
        
        for (int i = 0; i < clips.length; i++) {
            List<Integer> indList = map.computeIfAbsent(clips[i][0], k -> {
                LinkedList<Integer> l = new LinkedList<>();
                return l;
            });
            indList.add(i);
        }
        
        dfsSearch(clips, map, T, 0, res);
        if (res.get(0) == null) {
            return -1;
        }
        return res.get(0);
    }
}
