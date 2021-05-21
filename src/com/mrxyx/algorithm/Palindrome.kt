package com.mrxyx.algorithm

import com.mrxyx.algorithm.model.ListNode

/**
 * 回文
 */
class Palindrome {

    fun isPalindromeString(param: String): Boolean {
        var left = 0
        var right = param.length - 1
        while (left < right) {
            if (param[left] != param[right]) return false
            left++
            right--
        }
        return false
    }

    lateinit var left: ListNode

    /**
     * 回文链表
     * https://leetcode-cn.com/problems/palindrome-linked-list/
     */
    fun isPalindromeList(head: ListNode): Boolean {
        left = head
        return traverse(head)
    }

    //后序遍历
    private fun traverse(right: ListNode): Boolean {
        var res = traverse(right.next)
        res = res && right.`val` == left.`val`
        left = left.next
        return res
    }

    /**
     *  优化空间复杂度
     */
    fun isPalindromeListB(head: ListNode): Boolean {
        var slow = head
        var fast: ListNode? = head
        while (fast != null && fast.next != null) {
            slow = slow.next
            fast = fast.next.next
        }
        if (fast != null) slow = slow.next
        var left = head
        var right = reverse(slow)
        while (right != null) {
            if (left.`val` != right.`val`)
                return false
            left = left.next
            right = right.next
        }
        return true
    }

    /**
     * 反转链表
     */
    private fun reverse(slow: ListNode): ListNode? {
        var pre: ListNode? = null
        var cur = slow
        while (cur != null) {
            val nxt = cur.next
            cur.next = pre
            pre = cur
            cur = nxt
        }
        return  pre
    }
}