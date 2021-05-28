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
        if (root == null) return null
        connectTwoNode(root.left, root.right)
        return root
    }

    private fun connectTwoNode(left: TreeNode?, right: TreeNode?) {
        if (left == null || right == null) return
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
    fun flatten(root: TreeNode?) {
        if (root == null) return
        //拉直左右节点
        flatten(root.left)
        flatten(root.right)

        val left = root.left
        val right = root.right
        //将左子树作为右子树
        root.left = null
        root.right = left

        var p: TreeNode = root
        while (p.right != null)
            p = p.right
        p.right = right
    }

    /**
     * 最大二叉树
     * https://leetcode-cn.com/problems/maximum-binary-tree/
     */
    fun constructMaxBinaryTree(nums: IntArray): TreeNode? {
        return build(nums, 0, nums.size - 1)
    }

    private fun build(nums: IntArray, lo: Int, hi: Int): TreeNode? {
        if (nums.isEmpty()) return null
        var index = 0
        var maxVal = Int.MIN_VALUE
        //得到最大值index
        for (i in lo..hi) {
            if (nums[i] > maxVal) {
                maxVal = nums[i]
                index = i
            }
        }
        val root = TreeNode(maxVal)
        root.left = build(nums, lo, index - 1)
        root.right = build(nums, index + 1, hi)
        return root;
    }

    /**
     * 通过前序遍历和中序遍历构造二叉树
     * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     */
    fun buildTreeByPreIn(preorder: IntArray, inorder: IntArray): TreeNode? {
        return buildByPI(preorder, 0, preorder.size - 1, inorder, 0, inorder.size - 1)
    }

    private fun buildByPI(preorder: IntArray, preStart: Int, preEnd: Int, inorder: IntArray, inStart: Int, inEnd: Int): TreeNode? {
        if (preStart > preEnd) return null

        val rootVal = preorder[preStart]

        val root = TreeNode(rootVal)
        var mid = 0
        for (i in inStart..inEnd) {
            if (inorder[i] == rootVal) {
                mid = i
                break
            }
        }
        val leftSize = mid - inStart
        root.left = buildByPI(preorder, preStart + 1, preStart + leftSize, inorder, inStart, mid - 1)
        root.right = buildByPI(preorder, preStart + leftSize + 1, preEnd, inorder, mid + 1, inEnd)

        return root
    }

    /**
     * 通过后序和中序遍历结果构造二叉树
     * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
     */
    fun buildTreeByInPost(inorder: IntArray, postorder: IntArray): TreeNode? {
        return buildByIP(inorder, 0, inorder.size - 1, postorder, 0, postorder.size - 1)
    }

    private fun buildByIP(inorder: IntArray, inStart: Int, inEnd: Int, postorder: IntArray, postStart: Int, postEnd: Int): TreeNode? {
        if (inStart > inEnd) return null

        val rootVal = postorder[postEnd]
        var mid = 0
        for (i in inStart..inEnd) {
            if (inorder[i] == rootVal) {
                mid = i
                break
            }
        }

        val root = TreeNode(rootVal)
        val leftSize = mid - inStart
        root.left = buildByIP(inorder, inStart, mid - 1, postorder, postStart, postStart + leftSize - 1)
        root.right = buildByIP(inorder, mid + 1, inEnd, postorder, postStart + leftSize, postEnd - 1)

        return root
    }
}