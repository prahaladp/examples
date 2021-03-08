class Solution {

    private void addToLeft(TreeNode r, Stack<TreeNode> s) {
        while (r != null) {
            // System.out.println("Adding " + r.val);
            s.push(r);
            r = r.left;
        }
    }
    
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> retVal = new LinkedList<>();
        Stack<TreeNode> one = new Stack<>();
        Stack<TreeNode> two = new Stack<>();
        
        addToLeft(root1, one);
        addToLeft(root2, two);
        
        while (one.size() != 0 || two.size() != 0) {
            // System.out.println("Size  = " + one.size() + " " + two.size());
            if (one.size() == 0) {
                TreeNode n = two.pop();
                retVal.add(n.val);
                addToLeft(n.right, two);
                continue;
            }
            if (two.size() == 0) {
                TreeNode n = one.pop();
                retVal.add(n.val);
                addToLeft(n.right, one);
                continue;
            }
            TreeNode l = one.pop();
            TreeNode r = two.pop();
            // System.out.println("Comparing " + l.val + " " + r.val);
            if (l.val < r.val) {
                retVal.add(l.val);
                two.push(r);
                addToLeft(l.right, one);
                continue;
            }
            retVal.add(r.val);
            one.push(l);
            addToLeft(r.right, two);
        }
        return retVal;
    }
}
