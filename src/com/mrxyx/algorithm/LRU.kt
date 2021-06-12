package com.mrxyx.algorithm

/**
 * LRU算法
 */
class LRU(capacity: Int) {
    internal class Node(var key: Int, var `val`: Int) {
        var next: Node? = null
        var prev: Node? = null
    }

    internal class DoubleList {
        // 头尾虚节点
        private val head = Node(0, 0)
        private val tail = Node(0, 0)

        // 链表元素数
        private var size: Int

        // 在链表尾部添加节点 x，时间 O(1)
        fun addLast(x: Node) {
            x.prev = tail.prev
            x.next = tail
            tail.prev!!.next = x
            tail.prev = x
            size++
        }

        // 删除链表中的 x 节点（x 一定存在）
        // 由于是双链表且给的是目标 Node 节点，时间 O(1)
        fun remove(x: Node?) {
            x!!.prev!!.next = x.next
            x.next!!.prev = x.prev
            size--
        }

        // 删除链表中第一个节点，并返回该节点，时间 O(1)
        fun removeFirst(): Node? {
            if (head.next === tail) return null
            val first = head.next
            remove(first)
            return first
        }

        // 返回链表长度，时间 O(1)
        fun size(): Int {
            return size
        }

        init {
            // 初始化双向链表的数据
            head.next = tail
            tail.prev = head
            size = 0
        }
    }

    // key -> Node(key, val)
    private var map: HashMap<Int, Node>? = null

    // Node(k1, v1) <-> Node(k2, v2)...
    private var cache: DoubleList? = null

    // 最大容量
    private var cap = 0

    init {
        cap = capacity
        map = HashMap()
        cache = DoubleList()
    }

    /* 将某个 key 提升为最近使用的 */
    private fun makeRecently(key: Int) {
        val x = map!![key]
        // 先从链表中删除这个节点
        cache!!.remove(x)
        // 重新插到队尾
        cache!!.addLast(x!!)
    }

    /* 添加最近使用的元素 */
    private fun addRecently(key: Int, `val`: Int) {
        val x = Node(key, `val`)
        // 链表尾部就是最近使用的元素
        cache!!.addLast(x)
        // 别忘了在 map 中添加 key 的映射
        map!![key] = x
    }

    /* 删除某一个 key */
    private fun deleteKey(key: Int) {
        val x = map!![key]
        // 从链表中删除
        cache!!.remove(x)
        // 从 map 中删除
        map!!.remove(key)
    }

    /* 删除最久未使用的元素 */
    private fun removeLeastRecently() {
        // 链表头部的第一个元素就是最久未使用的
        val deletedNode = cache!!.removeFirst()
        // 同时别忘了从 map 中删除它的 key
        val deletedKey = deletedNode!!.key
        map!!.remove(deletedKey)
    }

    operator fun get(key: Int): Int {
        if (!map!!.containsKey(key)) {
            return -1
        }
        // 将该数据提升为最近使用的
        makeRecently(key)
        return map!![key]!!.`val`
    }

    fun put(key: Int, `val`: Int) {
        if (map!!.containsKey(key)) {
            // 删除旧的数据
            deleteKey(key)
            // 新插入的数据为最近使用的数据
            addRecently(key, `val`)
            return
        }
        if (cap == cache!!.size()) {
            // 删除最久未使用的元素
            removeLeastRecently()
        }
        // 添加为最近使用的元素
        addRecently(key, `val`)
    }
}