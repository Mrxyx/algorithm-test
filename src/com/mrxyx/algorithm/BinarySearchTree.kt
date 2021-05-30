package com.mrxyx.algorithm

import com.mrxyx.algorithm.model.TreeNode

/**
 * 二叉搜索树
 */
class BinarySearchTree {


    /**
     * 寻找第 K 小的元素
     * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
     * point: BST（二叉搜索树）中序遍历就是升序排列
     */
    fun kthSmallest(root:TreeNode, k:Int):Int {
        traverse(root, k)
        return res
    }
    //结果
    private var res = 0
    //当前节点排名
    private var rank = 0
    private fun traverse(root: TreeNode?, k: Int) {
        if(root ==null) return
        traverse(root.left , k)
        //中序遍历
        rank ++
        //找到第k小元素
        if(k == rank) {
            res = root.`val`
            return
        }
        traverse(root.right, k)
    }

    /**
     * BST 转化累加树
     * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
     * https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/
     * point:BST（二叉搜索树）中序遍历就是升序排列 调整遍历顺序可降序排列
     */
    fun convertBST(root: TreeNode) :TreeNode{
        traverse(root)
        return root
    }

    // 累加和
    private var sum = 0
    private fun traverse(root: TreeNode?) {
        if (root == null) return
        //先遍历右子树 降序遍历
        traverse(root.right)
        //中序遍历
        sum += root.`val`
        root.`val` = sum
        traverse(root.left)
    }
}