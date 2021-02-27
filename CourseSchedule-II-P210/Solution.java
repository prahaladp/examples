class Solution {
    public int[] findOrder(int numCourses, int[][] req) {
        List<Integer> collected = new LinkedList<>();
        int ind = 0;
        int[] ret = new int[numCourses];
        
        while (true) {
            Map<Integer, Integer> inMap = new HashMap<>();
            Set<Integer> last=new HashSet<>();
            for (int i = 0; i < req.length; i++) {
            
                if (collected.contains(req[i][1]) == true) {
                    if (collected.contains(req[i][0]) == false) {
                        last.add(req[i][0]);
                    }
                    continue;
                }
            
                int s = inMap.computeIfAbsent(req[i][1], (k)->0);
                int e = inMap.computeIfAbsent(req[i][0], (k)->0);
                inMap.put(req[i][0], e + 1);
            }
        
            if (inMap.size() == 0) {
                for (Integer i : last) {
                    //System.out.println("last " + i);
                    ret[ind++] = i;
                    collected.add(i);
                }
                for (int i =0; i < numCourses; i++) {
                    if (collected.contains(i) == false) {
                        ret[ind++] = i;
                    }
                }
                break;
            }
            
            int count = 0;
            for (Map.Entry<Integer, Integer> e: inMap.entrySet()) {
                if (e.getValue() == 0) {
                    ret[ind++] = e.getKey();
                    //System.out.println("added " + e.getKey());
                    collected.add(e.getKey());
                    count++;
                }
            }
            if (count == 0) {
                return new int[0];
            }
        }

        return ret;
    }
}
