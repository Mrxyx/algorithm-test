package com.mrxyx.algorithm

import java.util.*
import kotlin.collections.HashSet

class BFS {

    /**
     * 二叉树的最小深度
     * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
     */
    fun minDepth(root: TreeNode?): Int {
        if (root == null) return 0
        val queue = LinkedList<TreeNode?>()
        queue.add(root)
        var depth = 1
        while (!queue.isEmpty()) {
            val sz = queue.size
            for (i in 0 until sz) {
                val node = queue.poll()
                if (node?.leftChild == null && node?.rightChild == null) return depth
                if (node.leftChild != null) queue += node.leftChild
                if (node.rightChild != null) queue += node.rightChild
            }
            depth++
        }
        return 0
    }

    //节点
    class TreeNode(var value: Int, var leftChild: TreeNode? = null, var rightChild: TreeNode? = null)

    /**
     * 打开锁的最少次数
     * https://leetcode-cn.com/problems/open-the-lock/
     */
    fun openLock(deads: Array<String>, target: String): Int {
        val dead = HashSet<String>()
        //Dead Num
        for (d in deads) dead.add(d)
        //已访问过密码
        val visited = HashSet<String>()
        val q: Queue<String> = LinkedList()
        //当前步数
        var step = 0
        q.offer("0000")
        visited.add("0000")

        while (!q.isEmpty()) {
            val s = q.size
            var up: String
            var down: String
            for (i in 0 until s) {
                val cur = q.poll()
                if (dead.contains(cur)) continue
                if (target == cur) return step
                for (j in 0..4) {
                    up = plusOne(cur, j)
                    if (!visited.contains(up)) {
                        q.offer(up)
                        visited.add(up)
                    }
                    down = minusOne(cur, j)
                    if (!visited.contains(down)) {
                        q.offer(down)
                        visited.add(down)
                    }
                }
            }
            step++
        }
        return -1
    }

    //减1
    private fun minusOne(cur: String?, j: Int): String {
        if (cur == null) return ""
        val ch = cur.toCharArray()
        ch[j] = if (ch[j] == '0') '9' else ch[j] + 1
        return ch[j].toString()
    }

    //加1
    private fun plusOne(cur: String?, j: Int): String {
        if (cur == null) return ""
        val ch = cur.toCharArray()
        ch[j] = if (ch[j] == '9') '0' else ch[j] + 1
        return ch[j].toString()
    }

}