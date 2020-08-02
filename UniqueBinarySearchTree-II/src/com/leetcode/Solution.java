package com.leetcode;

import java.util.*;

class Solution {
    Map<Integer, Map<Integer, List<TreeNode>>> currentTreeNodes = new HashMap<>();

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<TreeNode> generateTrees(int n) {
        return generateTreeImpl(1, n);
    }

    private List<TreeNode> bothExists(int rootVal, List<TreeNode> leftTree, List<TreeNode> rightTree) {
        List<TreeNode> treeNodes = new LinkedList<>();
        for (int i = 0; i  < leftTree.size(); i++) {
            if (leftTree.get(i) == null) {
                continue;
            }

            for (int j = 0; j < rightTree.size(); j++) {

                if (rightTree.get(j) == null) {
                    continue;
                }

                TreeNode currentLeft = new TreeNode(leftTree.get(i).val);
                currentLeft.left = leftTree.get(i).left;
                currentLeft.right = leftTree.get(i).right;

                TreeNode currentRight = new TreeNode(rightTree.get(j).val);
                currentRight.left = rightTree.get(j).left;
                currentRight.right = rightTree.get(j).right;

                TreeNode currentRoot = new TreeNode(rootVal);
                currentRoot.left = currentLeft;
                currentRoot.right = currentRight;

                treeNodes.add(currentRoot);
            }
        }
        return treeNodes;
    }

    private List<TreeNode> onlyLeftExists(int rootVal, List<TreeNode> leftTree) {
        List<TreeNode> treeNodes = new LinkedList<>();
        for (int i = 0; i  < leftTree.size(); i++) {
            if (leftTree.get(i) == null) {
                continue;
            }

            TreeNode currentLeft = new TreeNode(leftTree.get(i).val);
            currentLeft.left = leftTree.get(i).left;
            currentLeft.right = leftTree.get(i).right;

            TreeNode currentRoot = new TreeNode(rootVal);
            currentRoot.left = currentLeft;
            currentRoot.right = null;

            treeNodes.add(currentRoot);
        }
        return treeNodes;
    }

    private List<TreeNode> onlyRightExists(int rootVal, List<TreeNode> rightTree) {
        List<TreeNode> treeNodes = new LinkedList<>();
        for (int i = 0; i  < rightTree.size(); i++) {
            if (rightTree.get(i) == null) {
                continue;
            }

            TreeNode currentRight = new TreeNode(rightTree.get(i).val);
            currentRight.left = rightTree.get(i).left;
            currentRight.right = rightTree.get(i).right;


            TreeNode currentRoot = new TreeNode(rootVal);
            currentRoot.left = null;
            currentRoot.right = currentRight;

            treeNodes.add(currentRoot);
        }
        return treeNodes;
    }



    private List<TreeNode> generateTreeImpl(int rootVal, int start, int end) {
        // System.out.println("generateTreeImpl " + rootVal + " " + start + " " + end);
        List<TreeNode> treeNodes = new LinkedList<>();

        List<TreeNode> leftTree = generateTreeImpl(start, rootVal - 1);
        List<TreeNode> rightTree = generateTreeImpl(rootVal + 1, end);

        if (leftTree.size() != 0 && rightTree.size() != 0) {
            treeNodes.addAll(bothExists(rootVal, leftTree, rightTree));
        } else if (leftTree.size() != 0) {
            treeNodes.addAll(onlyLeftExists(rootVal, leftTree));
        } else if (rightTree.size() != 0) {
            treeNodes.addAll(onlyRightExists(rootVal, rightTree));
        }

        currentTreeNodes.putIfAbsent(start, new HashMap<>());
        Map<Integer, List<TreeNode>> se = currentTreeNodes.get(start);
        se.put(end, treeNodes);

        // System.out.println("generateTreeImpl size " + rootVal + " " + start + " " + end + " " + treeNodes.size());

        return treeNodes;
    }

    private List<TreeNode> generateTreeImpl(int start, int end) {
        if (start > end) {
            return new LinkedList<>();
        }

        if (currentTreeNodes.get(start) != null && currentTreeNodes.get(start).get(end) != null) {
            return currentTreeNodes.get(start).get(end);
        }

        List<TreeNode> nl = new LinkedList<>();

        if (start == end) {
            currentTreeNodes.putIfAbsent(start, new HashMap<>());
            Map<Integer, List<TreeNode>> se = currentTreeNodes.get(start);

            TreeNode n = new TreeNode(start);
            nl.add(n);

            se.put(end, nl);

            return nl;
        }

        for (int i = start; i <= end; i++) {
            List trees = generateTreeImpl(i, start, end);
            nl.addAll(trees);
        }

        Map<Integer, List<TreeNode>> se = currentTreeNodes.putIfAbsent(start, new HashMap<>());
        se.put(end, nl);

        // System.out.println("generateTreeImpl " + start + " " + end + " size " + nl.size());

        return nl;
    }
}