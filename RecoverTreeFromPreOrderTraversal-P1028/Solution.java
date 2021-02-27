class Solution {
    class Node {
        int val;
        int lvl;
        TreeNode tn;
        Node(int val, int lvl, TreeNode tn) {
            this.val = val;
            this.lvl = lvl;
            this.tn = tn;
        }
    };
    
    public TreeNode recoverFromPreorder(String s) {
        Stack<Node> ns = new Stack<>();
        int dash = 0;
        int ind = 0;
        int num = 0;
        TreeNode root = null;
        for(char c : s.toCharArray()) {
            if (c == '-') {
                dash++;
                ind++;
                continue;
            }
            
            num = num * 10 + (int)(c - '0');
            if (ind == s.length() - 1 || s.charAt(ind+1) == '-') {
                // we have a number to insert
                TreeNode ins = new TreeNode(num);
                Node n = new Node(num, dash, ins);
                if (dash == 0) {
                    root = ins;
                    ns.push(n);
                    ind++;
                    num=0;
                    dash=0;
                    continue;
                }
                
                while (ns.isEmpty() == false) {
                    Node top = ns.pop();
                    if (top.lvl == dash - 1) {
                        if (top.tn.left == null) {
                            top.tn.left = ins;
                        } else {
                            top.tn.right = ins;
                        }
                        ns.push(top);
                        ns.push(n);
                        break;
                    }
                }
                num = 0;
                dash = 0;
            }
            ind++;
        } 
        return root;
    }
}
