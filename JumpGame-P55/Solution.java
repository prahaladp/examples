enum Index {
    GOOD, BAD, UNKNOWN
}

class Solution {
    private boolean checkJumps(int c, int l, int[] nums, Map<Integer, Boolean> v) {
        if (c == l-1) {
            v.put(c, true);
            return true;
        }
        if (l - c <= nums[c]) {
            v.put(c, true);
            return true;
        }
        for (int i = 1; i <= nums[c] && i+c < l; i++) {
            if (v.get(i+c) != null) {
                if (v.get(i+c) == true) {
                    v.put(c, true);
                    return true;
                }
                continue;
            }
            if (checkJumps(i+c, l, nums, v) == true) {
                v.put(c, true);
                return true;
            }
        }
        v.put(c, false);
        return false;
    }
    
    public boolean canJump1(int[] nums) {
        Map<Integer, Boolean> m = new HashMap<>();
        return checkJumps(0, nums.length, nums, m);
        /*
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> v = new HashSet<>();
        
        q.add(0);
        v.add(0);
        int l = nums.length-1;
        while (q.isEmpty() == false) {
            Integer c = q.remove();
            if (l - c <= nums[c]) {
                return true;
            }
            for (int i = 1; i <= nums[c] && i+c < l; i++) {
                if (v.contains(i + c) == false) {
                    v.add(i+c);
                    q.add(i+c);
                }
            }
        }
        return false;
        */
    }
    
    Index[] memo;

    public boolean canJumpFromPosition(int position, int[] nums) {
        if (memo[position] != Index.UNKNOWN) {
            return memo[position] == Index.GOOD ? true : false;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                memo[position] = Index.GOOD;
                return true;
            }
        }

        memo[position] = Index.BAD;
        return false;
    }

    public boolean canJump(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        return canJumpFromPosition(0, nums);
    }
    
}
