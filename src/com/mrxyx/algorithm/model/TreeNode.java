package com.mrxyx.algorithm.model;

/**
 * 二叉树
 */
public class TreeNode {
    int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode next;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
