package com.mrxyx.algorithm

/**
 * 数组
 */
class Array {

    /**
     * 吃香蕉
     * https://leetcode-cn.com/problems/koko-eating-bananas/
     */
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        val max = getMax(piles)
        var left = 1
        var right = max + 1
        while (left < right) {
            val mid = left + (right - left) / 2
            //是否可以吃完
            if (canFinish(piles, mid, h)) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }

    private fun canFinish(piles: IntArray, mid: Int, h: Int): Boolean {
        var time = 0
        for (i in piles) {
            time += timeOf(i, mid)
        }
        return time <= h
    }

    private fun timeOf(i: Int, mid: Int): Int {
        return i / mid + if (i % mid > 0) 1 else 0
    }

    private fun getMax(piles: IntArray): Int {
        var max = 0
        for (i in piles) {
            max = i.coerceAtLeast(max)
        }
        return max
    }

    /**
     * 在 D 天内送达包裹的能力
     * https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/
     */
    fun shipWithinDays(weights: IntArray, days: Int): Int {
        //最小值
        var left = getMax(weights)
        //最大值
        var right = getSum(weights) + 1
        while (left < right) {
            val mid = left + (right - left) / 2
            //是否可以完成
            if (canShipFinish(weights, mid, days)) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }

    private fun canShipFinish(weights: IntArray, mid: Int, days: Int): Boolean {
        var i = 0
        for (day in 0 until days) {
            var maxCap = mid
            while (weights[i].let { maxCap -= it; maxCap } >= 0) {
                i++
                if (i == weights.size) return true
            }
        }
        return false
    }

    private fun getSum(weights: IntArray): Int {
        var sum = 0
        for (weight in weights) {
            sum += weight
        }
        return sum
    }
}