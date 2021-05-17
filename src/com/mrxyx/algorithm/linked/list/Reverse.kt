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
}