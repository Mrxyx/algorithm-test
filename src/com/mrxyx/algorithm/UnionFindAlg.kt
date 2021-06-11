package com.mrxyx.algorithm

/**
 * 并查集 算法
 */
class UnionFindAlg {

    /**
     * 并查集查询算法
     */
    internal class UF(private var count: Int) {
        // 存储若干棵树
        private val parent = IntArray(count)

        // 记录树的“重量”
        private val size = IntArray(count)

        /**
         *  将 p 和 q 连通
         * */
        fun union(p: Int, q: Int) {
            val rootP = find(p)
            val rootQ = find(q)
            if (rootP == rootQ) return

            // 优化点 小树接到大树下面，较平衡
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP
                size[rootP] += size[rootQ]
            } else {
                parent[rootP] = rootQ
                size[rootQ] += size[rootP]
            }
            count--
        }

        /**
         * 判断 p 和 q 是否互相连通
         */
        fun connected(p: Int, q: Int): Boolean {
            val rootP = find(p)
            val rootQ = find(q)
            // 处于同一棵树上的节点，相互连通
            return rootP == rootQ
        }

        /**
         * 返回节点 x 的根节点
         */
        private fun find(x: Int): Int {
            var x = x
            while (parent[x] != x) {
                // 优化点 进行路径压缩
                parent[x] = parent[parent[x]]
                x = parent[x]
            }
            return x
        }

        init {
            for (i in 0 until count) {
                parent[i] = i
                size[i] = 1
            }
        }
    }

    /**
     * 等式方程的可满足性
     * https://leetcode-cn.com/problems/satisfiability-of-equality-equations/
     * step 1、将equations中的算式根据==和!=分成两部分，
     * step 2、 先处理==算式，使得他们通过相等关系各自勾结成门派；
     * step 3、 然后处理!=算式，检查不等关系是否破坏了相等关系的连通性。
     */
    fun equationsPossible(equations: Array<String>): Boolean {
        val uf = UF(26)

        for (eq: String in equations) {
            if (eq[1] == '=') {
                val x = eq[0]
                val y = eq[3]
                uf.union(x - 'a', y - 'a')
            }
        }

        for (eq: String in equations) {
            if (eq[1] == '!') {
                val x = eq[0]
                val y = eq[3]
                //判断是否连通
                if (uf.connected(x - 'a', y - 'a')) return false
            }
        }
        return true
    }
}