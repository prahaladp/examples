class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> v = new HashSet<>();
        Map<String, Integer> mc = new HashMap<>();

        q.add(id);
        v.add(id);
        while (level > 0) {
            Queue<Integer> nq = new LinkedList<>();
            mc.clear();
            for (Integer i : q) {
                for (int j = 0; j < friends[i].length; j++) {
                    if (v.contains(friends[i][j]) == false) {
                        // System.out.println("Adding node " + friends[i][j]);
                        nq.add(friends[i][j]);
                        for (String movie : watchedVideos.get(friends[i][j])) {
                            int mcount = mc.computeIfAbsent(movie, (k) -> 0);
                            mc.put(movie, mcount+1);
                        }
                        v.add(friends[i][j]);
                    }
                }
            }
            q = nq;
            level--;
        }

        TreeMap<Integer, List<String>> tm = new TreeMap<>();
        for (Map.Entry e : mc.entrySet()) {
            List<String> mcs = tm.get(e.getValue());
            if (mcs == null) {
                mcs = new ArrayList<>();
            }
            tm.put((Integer)e.getValue(), mcs);

            mcs.add((String)e.getKey());
            System.out.println(e.getKey() + " : " + e.getValue());
        }

        List<String> s = new ArrayList<>();
        for (Map.Entry e : tm.entrySet()) {
            Collections.sort((List<String>)e.getValue());
            s.addAll(((List<String>)e.getValue()));
        }
        return s;
    }
}
