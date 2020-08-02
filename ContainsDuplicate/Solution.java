class Solution {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> numMap = new HashMap<>();
        
        for (int i : nums) {
            if (numMap.get(i) != null) {
                return true;
            }
            numMap.put(i, 1);
        }
        return false;
    }
}
