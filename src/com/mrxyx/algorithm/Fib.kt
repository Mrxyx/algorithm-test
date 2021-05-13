package com.mrxyx.algorithm

/**
 * 斐波那契数列
 */
class Fib {

    /**
     * 暴力递归
     * @param n 入参
     */
    fun fibV1(n: Int): Int {
        if (n == 1 || n == 2) return 1
        return fibV1(n - 1) + fibV1(n - 2)
    }

    /**
     * 带备忘录的递归 自顶向下
     */
    fun fibV2(n: Int): Int {
        val mem: Array<Int> = Array(n) { 0 }
        return fibV2Helper(mem, n)
    }

    private fun fibV2Helper(mem: Array<Int>, n: Int): Int {
        if (n == 1 || n == 2) return 1
        if (mem[n - 1] != 0) return mem[n - 1]
        mem[n - 1] = fibV2Helper(mem, n - 1) + fibV2Helper(mem, n - 2)
        return mem[n - 1]
    }

    /**
     * 动态规划 自底向上
     */
    fun fibV3(n: Int): Int {
        val dp: Array<Int> = Array(n + 1) { 0 }
        dp[1] = 1
        dp[2] = 1
        for (i in 3..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }

    /**
     * 动态规划 空间复杂度O(1)
     */
    fun fibV4(n: Int): Int {
        if (n == 1 || n == 2) return 1
        var pre = 1
        var curr = 1
        var sum = 1
        for (i in 3..n) {
            sum = pre + curr
            pre = curr
            curr = sum
        }
        return sum
    }
}