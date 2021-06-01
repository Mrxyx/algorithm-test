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
    fun kthSmallest(root: TreeNode, k: Int): Int {
        traverse(root, k)
        return res
    }

    //结果
    private var res = 0

    //当前节点排名
    private var rank = 0
    private fun traverse(root: TreeNode?, k: Int) {
        if (root == null) return
        traverse(root.left, k)
        //中序遍历
        rank++
        //找到第k小元素
        if (k == rank) {
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
    fun convertBST(root: TreeNode): TreeNode {
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

    /**
     * 判断二叉搜索树是否合法
     */
    fun isValidBST(root: TreeNode?): Boolean {
        return dfs(root, null, null)
    }

    private fun dfs(root: TreeNode?, min: Int?, max: Int?): Boolean {
        root ?: let {
            return true
        }
        min?.let {
            if (min >= root.`val`) return false
        }
        max?.let {
            if (max <= root.`val`) return false
        }
        return dfs(root.left, min, root.`val`) && dfs(root.right, root.`val`, max)
    }

    /**
     * 二叉搜索树中的搜索 递归解法
     * https://leetcode-cn.com/problems/search-in-a-binary-search-tree
     */
    fun isInBST(root: TreeNode?, target: Int): Boolean {
        root ?: let {
            return false
        }

        if (root.`val` == target) return true
        if (root.`val` > target) return isInBST(root.left, target)
        if (root.`val` < target) return isInBST(root.right, target)
        return false
    }

    /**
     * 二叉搜索树中的搜索 迭代解法
     * https://leetcode-cn.com/problems/search-in-a-binary-search-tree
     */
    fun isInBST2(root: TreeNode?, target: Int): Boolean {
        var tmp: TreeNode? = root
        while (tmp != null) {
            if (tmp.`val` == target) return true
            tmp = if (tmp.`val` > target) tmp.left else tmp.right
        }
        return false
    }

    /**
     * 二叉搜索树插入 递归
     * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
     */
    fun insertBTS(root: TreeNode?, value: Int): TreeNode {
        root ?: let {
            return TreeNode(value)
        }
        if (root.`val` > value) root.left = insertBTS(root.left, value)
        if (root.`val` < value) root.right = insertBTS(root.right, value)
        return root
    }

    /**
     * 二叉搜索树插入 迭代
     * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
     */
    fun insertBTS2(root: TreeNode?, value: Int): TreeNode? {
        root ?: let {
            return TreeNode(value)
        }
        var tmp = root
        while (tmp != null) {
            //如果大于
            if (tmp.`val` > value) {
                if (tmp.left != null) {
                    tmp = tmp.left
                } else {
                    tmp.left = TreeNode(value)
                    break
                }
            } else if (tmp.`val` < value) {
                if (tmp.right != null) {
                    tmp = tmp.right
                } else {
                    tmp.right = TreeNode(value)
                    break
                }
            }
        }
        return root
    }
}