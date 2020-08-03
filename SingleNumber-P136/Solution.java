class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = numMap.get(nums[i]);
            if (count != null) {
                count = count + 1;
            } else {
                count = 1;
            }
            numMap.put(nums[i], count);
        }
        for (Map.Entry<Integer, Integer> e : numMap.entrySet()) {
            if (e.getValue() == 1) {
                return e.getKey();
            }
        }
        return -1;
    }
}
