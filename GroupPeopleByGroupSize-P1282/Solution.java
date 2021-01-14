
class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> res = new LinkedList<>();
        Map<Integer, List<Integer>> currentMap = new HashMap<>();

        for (int i = 0 ; i < groupSizes.length; i++) {
            if (groupSizes[i] == 1) {
                List<Integer> r = new LinkedList<>();
                r.add(i);
                res.add(r);
                continue;
            }
            List<Integer> c = currentMap.computeIfAbsent(groupSizes[i],
                                                        (k) ->  {
                                                            return new LinkedList<Integer>();
                                                        });
            c.add(i);
            if (c.size() == groupSizes[i]) {
                res.add(c);
                currentMap.remove(groupSizes[i]);
            }
        }
        return res;
    }
}
