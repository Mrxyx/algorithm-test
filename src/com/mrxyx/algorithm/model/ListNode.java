package com.mrxyx.algorithm.model;

/**
 * 链表
 */
public class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode getNext() {
        return next;
    }

    public int getVal() {
        return val;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
