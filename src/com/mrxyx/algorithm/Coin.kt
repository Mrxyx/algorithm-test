package com.mrxyx.algorithm

import kotlin.math.min

/**
 * 零钱兑换
 */
class Coin {
    private var res: Int = Int.MAX_VALUE

    /**
     * 暴力递归 自顶向下
     */
    fun coinChangeV1(coins: Array<Int>, amount: Int): Int {
        if (coins.isEmpty()) return 0;
        coinChangeHelperV1(coins, amount, 0)
        return if (res == Int.MAX_VALUE) -1 else res
    }

    /**
    # 初始化 base case
    dp[0][0][...] = base
    # 进行状态转移
    for 状态1 in 状态1的所有取值：
    for 状态2 in 状态2的所有取值：
    for ...
    dp[状态1][状态2][...] = 求最值(选择1，选择2...)
     **/
    private fun coinChangeHelperV1(coins: Array<Int>, amount: Int, count: Int) {
        if (amount < 0) return
        if (amount == 0) res = min(res, count)
        for (coin in coins)
            coinChangeHelperV1(coins, amount - coin, count + 1)
    }

    /**
     * 备忘录递归 自顶向下
     */
    lateinit var cache: Array<Int>
    fun coinChangeV2(coins: Array<Int>, amount: Int): Int {
        if (coins.isEmpty()) return 0;
        cache = Array(amount) { 0 }
        return coinChangeHelperV2(coins, amount)
    }

    private fun coinChangeHelperV2(coins: Array<Int>, amount: Int): Int {
        if (amount < 0) return -1
        if (amount == 0) return 0
        if (cache[amount-1] != 0) return cache[amount-1]
        var res: Int
        var min: Int = Int.MAX_VALUE
        for (coin in coins) {
            res = coinChangeHelperV2(coins, amount - coin)
            if (res in 0..min) {
                min = res + 1
            }
        }
        cache[amount - 1] = if (min == Integer.MAX_VALUE) -1 else min
        return cache[amount - 1]
    }

    /**
     * 动态规划
     */
    fun coinChangeV3(coins: Array<Int>, amount: Int): Int {
        if (coins.isEmpty()) return -1
        val cache: Array<Int> = Array(amount + 1) { 0 }
        cache[0] = 0
        var min: Int
        for (i in 1..amount) {
            min = Int.MAX_VALUE
            for (coin in coins)
                if (i - coin >= 0 && cache[i - coin] < min)
                    min = cache[i - coin] + 1
            cache[i] = min
        }
        return if (cache[amount] == Int.MAX_VALUE) -1 else cache[amount]
    }
}