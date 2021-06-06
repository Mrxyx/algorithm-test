package com.mrxyx.algorithm

import com.mrxyx.algorithm.model.TreeNode
import java.util.*


/**
 * 二叉树
 */
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
        left.next = right

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
        return root
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

    /**
     * 寻找重复子树
     * https://leetcode-cn.com/problems/find-duplicate-subtrees/
     * step1 以我为根子树什么样
     * step2 以其他节点为根子树什么样
     */
    //记录子树 及其出现的次数
    private val memo = HashMap<String, Int>()

    //记录重复子树根节点
    private val res = LinkedList<TreeNode>()
    fun findDuplicateSubtrees(root: TreeNode?): List<TreeNode> {
        traverse(root)
        return res
    }

    private fun traverse(root: TreeNode?): String {
        if (root == null) return "#"

        val left = traverse(root.left)
        val right = traverse(root.right)

        //后序遍历
        val subTree = left + "," + right + "," + root.`val`

        val freq = memo.getOrDefault(subTree, 0)
        if (freq == 1) res.add(root)
        memo[subTree] = freq + 1
        return subTree
    }

    /**
     * 二叉树的序列化
     * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
     */
    fun serializeV1(root: TreeNode?): String {
        val sb = StringBuilder()
        serializeHelperV1(root, sb)
        return sb.toString()
    }

    private var sep = ","
    private var n = "null"
    private fun serializeHelperV1(root: TreeNode?, sb: StringBuilder) {
        if (root == null) {
            sb.append(n).append(sep)
            return
        }
        /****** 前序遍历位置 ******/
        sb.append(root.`val`).append(sep)
        /***********************/

        serializeHelperV1(root.left, sb)
        serializeHelperV1(root.right, sb)
    }

    /**
     * 二叉树的反序列化
     * 将字符串反序列化为二叉树结构
     */
    fun deserializeV1(data: String): TreeNode? {
        // 将字符串转化成列表
        val nodes = LinkedList<String>()
        for (s in data.split(sep).toTypedArray()) {
            nodes.addLast(s)
        }
        return deserializeV1(nodes)
    }

    private fun deserializeV1(nodes: LinkedList<String>): TreeNode? {
        if (nodes.isEmpty()) return null
        /****** 前序遍历位置  */
        // 列表最左侧就是根节点
        val first = nodes.removeFirst()
        if (first == n) return null
        val root = TreeNode(first.toInt())
        root.left = deserializeV1(nodes)
        root.right = deserializeV1(nodes)
        return root
    }

    /**
     * 二叉树的序列化
     * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
     */
    private var sb = StringBuilder()
    fun serializeV2(root: TreeNode?): String {
        serializeHelperV2(root)
        return sb.toString()
    }

    private fun serializeHelperV2(root: TreeNode?) {
        if (root == null) {
            sb.append(n).append(sep)
            return
        }
        serializeHelperV2(root.left)
        serializeHelperV2(root.right)


        /****** 后序遍历位置 ******/
        sb.append(root.`val`).append(sep)
        /***********************/
    }

    /**
     * 二叉树的反序列化
     * 将字符串反序列化为二叉树结构
     */
    fun deserializeV2(data: String): TreeNode? {
        val nodes = LinkedList<String>()
        for (s in data.split(sep)) {
            if (s.isEmpty()) continue
            nodes.addLast(s)
        }
        return deserializeV2(nodes)
    }

    private fun deserializeV2(nodes: LinkedList<String>): TreeNode? {
        if (nodes.isEmpty()) return null
        // 从后往前取出元素
        val last = nodes.removeLast()
        if (n == last) {
            return null
        }
        val root = TreeNode(last.toInt())
        // 限构造右子树，后构造左子树
        root.right = deserializeV2(nodes)
        root.left = deserializeV2(nodes)
        return root
    }

}