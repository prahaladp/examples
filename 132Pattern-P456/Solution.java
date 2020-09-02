class Solution {
    public boolean find132pattern(int[] nums) {
        List<Set<Integer>> gt = new LinkedList<>();
        List<Set<Integer>> lt = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            gt.add(new HashSet<>());
            lt.add(new HashSet<>());
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    gt.get(i).add(j);
                } else if (nums[j] < nums[i]) {
                    lt.get(i).add(j);
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (gt.get(i).size() > 0) {
                for (Iterator<Integer> iter = gt.get(i).iterator(); iter.hasNext();) {
                    int ind = iter.next();
                    Set<Integer> ni = new HashSet(lt.get(ind));
                    ni.retainAll(gt.get(i));
                    if (ni.size() > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
