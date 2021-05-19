package com.mrxyx.algorithm.linked.list

import com.mrxyx.algorithm.model.ListNode

/**
 * 反转链表
 */
class Reverse {

    /**
     * 反转整个链表
     */
    fun reverseAll(head: ListNode): ListNode {
        //base case
        if (head.next == null) return head
        val last = reverseAll(head.next)
        head.next.next = head
        head.next = null
        return last
    }

    //后继节点
    private lateinit var successor: ListNode

    /**
     * 反转链表前 N 个节点
     */
    fun reverseN(head: ListNode, n: Int): ListNode {
        if (n == 1) {
            successor = head.next
            return head
        }
        val last = reverseN(head.next, n - 1)
        head.next.next = head
        head.next = successor
        return last
    }

    /**
     * 反转链表的一部分
     *  https://leetcode-cn.com/problems/reverse-linked-list-ii/
     */
    fun reverseBetween(head: ListNode, left: Int, right: Int): ListNode {
        //base case
        if (left == 1) {
            return reverseN(head, right)
        }
        head.next = reverseBetween(head.next, left - 1, right - 1)
        return head
    }

    /**
     * 递归反转链表
     */
    fun reverse(a: ListNode?): ListNode? {
        var pre: ListNode? = null
        var cur = a
        var nxt: ListNode?
        while (cur != null) {
            nxt = cur.next
            cur.next = pre
            pre = cur
            cur = nxt;
        }
        return pre
    }

    /**
     * 递归反转区间[a,b)
     */
    fun reverse(a: ListNode?, b: ListNode?): ListNode? {
        var pre: ListNode? = null
        var cur = a
        var nxt: ListNode?
        while (cur != b) {
            nxt = cur?.next
            cur?.next = pre
            pre = cur
            cur = nxt;
        }
        return pre
    }

    /**
     * k个一组反转链表
     */
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (head == null) return null
        var a = head
        var b = head
        for (i in 0..k) {
            b = b?.next
        }
        val newHead = reverse(a, b)
        a.next = reverseKGroup(b, k)
        return newHead
    }
}