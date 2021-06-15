package com.mrxyx.algorithm


class LFU (capacity: Int){
    // key 到 val 的映射，我们后文称为 KV 表
    private var keyToVal: HashMap<Int, Int> = HashMap()
    // key 到 freq 的映射，我们后文称为 KF 表
    private var keyToFreq: HashMap<Int, Int> = HashMap()
    // freq 到 key 列表的映射，我们后文称为 FK 表
    var freqToKeys: HashMap<Int, LinkedHashSet<Int>> = HashMap()

    // 记录最小的频次
    private var minFreq = 0
    // 记录 LFU 缓存的最大容量
    private var cap = 0

    init{
        cap = capacity
        minFreq = 0
    }

    operator fun get(key: Int): Int {
        if (!(keyToVal.containsKey(key))) {
            return -1
        }
        // 增加 key 对应的 freq
        increaseFreq(key)
        return keyToVal[key]!!
    }

    fun put(key: Int, `val`: Int) {
        if (cap <= 0) return
        /* 若 key 已存在，修改对应的 val 即可 */
        if (keyToVal.containsKey(key)) {
            keyToVal[key] = `val`
            // key 对应的 freq 加一
            increaseFreq(key)
            return
        }

        /* key 不存在，需要插入 */
        /* 容量已满的话需要淘汰一个 freq 最小的 key */
        if (cap <= keyToVal.size) {
            removeMinFreqKey()
        }

        /* 插入 key 和 val，对应的 freq 为 1 */
        // 插入 KV 表
        keyToVal[key] = `val`
        // 插入 KF 表
        keyToFreq[key] = 1
        // 插入 FK 表
        freqToKeys.putIfAbsent(1, LinkedHashSet())
        freqToKeys[1]!!.add(key)
        // 插入新 key 后最小的 freq 肯定是 1
        minFreq = 1
    }

    private fun removeMinFreqKey() {
        // freq 最小的 key 列表
        val keyList = freqToKeys[minFreq]
        // 其中最先被插入的那个 key 就是该被淘汰的 key
        val deletedKey = keyList!!.iterator().next()
        /* 更新 FK 表 */
        keyList.remove(deletedKey)
        if (keyList.isEmpty()) {
            freqToKeys.remove(minFreq)
        }
        /* 更新 KV 表 */
        keyToVal.remove(deletedKey)
        /* 更新 KF 表 */
        keyToFreq.remove(deletedKey)
    }

    private fun increaseFreq(key: Int) {
        val freq = keyToFreq[key]!!
        /* 更新 KF 表 */keyToFreq[key] = freq + 1
        /* 更新 FK 表 */
        // 将 key 从 freq 对应的列表中删除
        freqToKeys[freq]!!.remove(key)
        // 将 key 加入 freq + 1 对应的列表中
        freqToKeys.putIfAbsent(freq + 1, LinkedHashSet())
        freqToKeys[freq + 1]!!.add(key)
        // 如果 freq 对应的列表空了，移除这个 freq
        if (freqToKeys[freq]!!.isEmpty()) {
            freqToKeys.remove(freq)
            // 如果这个 freq 恰好是 minFreq，更新 minFreq
            if (freq == minFreq) {
                minFreq++
            }
        }
    }
}