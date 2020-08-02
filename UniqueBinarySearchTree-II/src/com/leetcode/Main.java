package com.leetcode;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        Solution s = new Solution();
        List<Solution.TreeNode> treeNodes = s.generateTrees(3);
        System.out.println("Size of trees" + treeNodes.size());

        treeNodes = s.generateTrees(4);
        System.out.println("Size of trees " + treeNodes.size());

    }
}
