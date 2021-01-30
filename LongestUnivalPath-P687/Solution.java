class Solution {
    public int maxLength = 0;
    public Solution() {
        this.maxLength = 0;
    }
    
    public int recurseNode(TreeNode r, int al) {
        if (r == null) {
            this.maxLength = Math.max(al, this.maxLength);
            return al;
        }
        
        if (r.left == null && r.right == null) {
            this.maxLength = Math.max(al, this.maxLength);
            return al;
        }
        
        int nv = al;
        int lv = 0;
        if (r.left != null && r.left.val == r.val) {
            lv = recurseNode(r.left, al+1);
            nv = lv; 
            this.maxLength = Math.max(nv, this.maxLength);
        } else {
            recurseNode(r.left, 0);
        }
        if (r.right != null && r.right.val == r.val) {
            int rv = recurseNode(r.right, al+1);
            nv = Math.max(nv, rv);
            if (lv != 0) {
                this.maxLength = Math.max(this.maxLength, lv - al + rv - al);
            }
        } else {
            recurseNode(r.right, 0);
        }

        this.maxLength = Math.max(nv, this.maxLength);
        return nv;
    }
    
    public int longestUnivaluePath(TreeNode root) {
        recurseNode(root, 0);
        return this.maxLength;
    }
}
