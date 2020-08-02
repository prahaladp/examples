class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> numMap = new HashMap<>();
        
        for (int i = 0 ; i < nums.length; i++) {
            List<Integer> s = numMap.get(nums[i]);
            if (s == null) {
                s = new LinkedList<>();
            }
            s.add(i);
            numMap.put(nums[i], s);
        }
        
        for (Map.Entry<Integer, List<Integer>> s : numMap.entrySet()) {
            List<Integer> l = s.getValue();
            for (int i = 0; i < l.size(); i++) {
                for (int j = i + 1; j < l.size(); j++) {
                    if (l.get(j) - l.get(i) <= k) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
