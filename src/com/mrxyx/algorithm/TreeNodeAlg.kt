package com.mrxyx.algorithm

import com.mrxyx.algorithm.model.TreeNode

class TreeNodeAlg {

    /**
     * 反转二叉树
     * https://leetcode-cn.com/problems/invert-binary-tree/
     */
    fun invertTree(root: TreeNode?): TreeNode? {
        //base case
        if (root == null) return null
        /**前序遍历**/
        //当前节点 翻转左右子节点
        val tmp = root.left
        root.left = root.right
        root.right = tmp
        //左右节点依次翻转子节点
        invertTree(root.left)
        invertTree(root.right)
        return root
    }

    /**
     * 填充二叉树节点的右侧指针(完美二叉树)
     * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
     */
    fun connect(root: TreeNode?): TreeNode? {
        //base case
        if(root == null) return null
        connectTwoNode(root.left, root.right)
        return root
    }

    private fun connectTwoNode(left: TreeNode?, right: TreeNode?) {
        if(left == null || right == null) return
        /**前序遍历**/
        //连结左右节点
        left.next = right;

        //连接相同父节点的左右节点
        connectTwoNode(left.left, left.right)
        connectTwoNode(right.left, right.right)
        //连接不同父节点的两子节点 左节点的右节点 与 右节点的左节点
        connectTwoNode(left.right, right.left)
    }

    /**
     * 将二叉树展开为链表
     * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
     * step1: 左右节点拉直
     * step2: 左节点作为根节点的右节点 原来的右节点拼接在左节点的右节点
     */
    fun flatten(root:TreeNode?) {
        if(root == null) return
        //拉直左右节点
        flatten(root.left)
        flatten(root.right)

        val left = root.left
        val right = root.right
        //将左子树作为右子树
        root.left = null
        root.right = left

        var p:TreeNode = root
        while (p.right != null)
            p = p.right
        p.right = right
    }
}