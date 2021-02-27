class Solution {
    class Node {
        int sum;
        List<Integer> l;
        
        public Node(int sum, List<Integer> l) {
            this.sum = sum;
            this.l = l;
        }
    }
    public int constrainedSubsetSum(int[] nums, int k) {
        List<Node> c = new LinkedList<>();
        List<Integer> s = new LinkedList<>();
        Node mxn;
        
        s.add(nums.length-1);
        Node n = new Node(nums[nums.length-1], s);
        c.add(n);
        mxn = n;
        for (int i=nums.length-2;i >= 0; i--) {
            List<Node> newNodes = new LinkedList<>();
            for (Node ln : c) {
                if (ln.l.get(0) - i <= k) {
                    List<Integer> ns = new LinkedList<>();
                    ns.add(i);
                    ns.addAll(ln.l);
                    Node nn = new Node(nums[i] + ln.sum, ns);
                    
                    newNodes.add(nn);
                    if (nn.sum > mxn.sum) {
                        mxn = nn;
                    }
                } else {
                    //break;
                }
            }
            
            LinkedList<Integer> single = new LinkedList<>();
            single.add(i);
            Node singleN = new Node(nums[i], single);
            c.add(singleN);
            if (singleN.sum > mxn.sum) {
                mxn = singleN;
            }

            List<Node> t = new LinkedList<>();
            t.addAll(newNodes);
            t.addAll(c);
            c=t;
            
            //for (Node ln : c) {
            //    System.out.println(ln.l);
            //}
            //System.out.println("---");
        }
        
        return mxn.sum;
    }
}
