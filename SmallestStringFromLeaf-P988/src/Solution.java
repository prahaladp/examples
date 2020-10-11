import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	};
	
	public void startTest() {
		TreeNode t = new TreeNode(25);
		t.left = new TreeNode(1);		
		t.right = new TreeNode(3);
		
		t.left.left = new TreeNode(1);
		t.left.right = new TreeNode(3);
		
		t.right.left = new TreeNode(0);
		t.right.right = new TreeNode(2);

		System.out.println(smallestFromLeaf(t));

	}

    public String smallestFromLeaf(TreeNode root) {
        List<TreeNode> cq = new ArrayList<>();
        List<TreeNode> nq = new ArrayList<>();
        List<List<Integer>> leafList = new ArrayList<>();
        Map<TreeNode, List<Integer>> bm = new HashMap<>();
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        
        cq.add(root);
        bm.putIfAbsent(root, new ArrayList<>());
        bm.get(root).add(root.val);
        
        while (cq.isEmpty() == false) {
        		nq = new ArrayList<>();
        		pQueue.clear();
        		
        		for (TreeNode e : cq) {
        			System.out.printf("node  = %d %s %s\n", e.val, e.left, e.right);
        			if (e.left == null && e.right == null) {
        				leafList.add(bm.get(e));
        			} else {
        				if (e.left != null) {
        					nq.add(e.left);
        					bm.putIfAbsent(e.left, new ArrayList<>(bm.get(e)));
        					bm.get(e.left).add(e.left.val);
        				}
        				if (e.right != null) {
        					nq.add(e.right);
        					bm.putIfAbsent(e.right, new ArrayList<>(bm.get(e)));
        					bm.get(e.right).add(e.right.val);
        				}
        			}
        		}
        		cq = nq;
        }
        
		int ind = 0;
		List<List<Integer>> res;
		System.out.printf("Collected leafs = %s\n", leafList);
        while (true) {
        		int pmin = 26;
            res = new ArrayList<>();

        		for (int i = 0; i < leafList.size(); i++) {
        			if (leafList.get(i).size() - ind - 1 < 0) {
        				res.clear();
        				res.add(leafList.get(i));
        				break;
        			}
        			if (leafList.get(i).get(leafList.get(i).size() - ind - 1) == pmin) {
        				res.add(leafList.get(i));
        			} else if (leafList.get(i).get(leafList.get(i).size() - ind - 1) < pmin) {
        				res.clear();
        				res.add(leafList.get(i));
        				pmin = leafList.get(i).get(leafList.get(i).size() - ind - 1);
        			}
        		}
        		System.out.printf("Collected results = %s\n", res);

        		if (res.size() == 1) {
        			// end
        			break;
        		}
        		leafList = res;
        		ind++;
        }
                
        StringBuilder s = new StringBuilder();
        res.get(0).stream().forEach(i -> {
        		s.insert(0, (char)((int)'a' + i));
        });
        
        return s.toString();
    }
    
    public static void main(String[] args) {
    		Solution s = new Solution();
    		s.startTest();
    }
}