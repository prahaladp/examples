/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
   private class TreeStat {
        int minVal;
        int minValCount;
        int maxVal;
        int maxValCount;
    }

    private TreeStat inOrder(TreeNode n, Map<Integer, List<Integer>> l) {
        if (n == null) {
            return null;
        }

        TreeStat lt = inOrder(n.left, l);
        TreeStat rt = inOrder(n.right, l);

        TreeStat c = new TreeStat();
        int nCount = 1;
        if (lt == null && rt == null) {
            c.minValCount = c.maxValCount = 1;
            c.minVal = c.maxVal = n.val;
        } else {
            if (lt != null) {
                c.minVal = lt.minVal;
                c.minValCount = lt.minValCount;
                if (lt.maxVal == n.val) {
                    nCount += lt.maxValCount;
                    if (lt.minVal == n.val) {
                        c.minValCount = nCount;
                    }
                }
            } else {
                c.minVal = n.val;
                c.minValCount = nCount;
            }
            if (rt != null) {
                c.maxVal = rt.maxVal;
                c.maxValCount = rt.maxValCount;
                if (rt.minVal == n.val) {
                    nCount += rt.minValCount;
                    if (rt.maxVal == n.val) {
                        c.maxValCount = nCount;
                    }
                }
                if (lt == null) {
                    c.minValCount = nCount;
                }
            } else {
                c.maxVal = n.val;
                c.maxValCount = nCount;
            }
        }

        if (c.minVal == c.maxVal) {
            c.maxValCount = nCount;
            c.minValCount = nCount;
        }
        // System.out.println("node = " + n + " " + n.val + " " + c.minVal + " " + c.minValCount + " " + c.maxVal + " " + c.maxValCount);
        Set<Map.Entry<Integer, List<Integer>>> se = l.entrySet();
        if (se.size() == 0) {
            List<Integer> ni = new LinkedList<>();
            ni.add(n.val);
            l.put(nCount, ni);
            return c;
        }
        for (Map.Entry<Integer, List<Integer>> it: se) {
            if (it.getKey() > nCount) {
                // nothing to do, break;
                break;
            }
            if (nCount > it.getKey()) {
                l.clear();
                List<Integer> ni = new LinkedList<>();
                ni.add(n.val);
                l.put(nCount, ni);
            } else {
                List<Integer> ni = it.getValue();
                ni.add(n.val);
                l.put(nCount, ni);
            }
            break;
        }
        return c;
    }

    public int[] findMode(TreeNode root) {
        Map<Integer, List<Integer>> l = new HashMap<>();
        TreeStat c = inOrder(root, l);
        List<Integer> li = new LinkedList<>();
        l.entrySet().forEach(e -> li.addAll(e.getValue()));
        return li.stream().mapToInt(i -> i).toArray();
    }
}
