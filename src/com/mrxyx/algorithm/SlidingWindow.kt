package com.mrxyx.algorithm

/**
 * 滑动窗口
 */
class SlidingWindow {

    /**
     * 最小覆盖字串
     * https://leetcode-cn.com/problems/minimum-window-substring
     */
    fun minWindow(s: String, t: String): String {
        val need = HashMap<Char, Int>()
        val window = HashMap<Char, Int>()
        //初始化
        for (c in t) {
            need[c] = need.getOrDefault(c, 0) + 1
        }

        var left = 0
        var right = 0
        var valid = 0

        var start = 0
        var legth = Int.MAX_VALUE
        //移动右边界
        while (right < s.length) {
            val char = s[right]
            right++
            if (need.containsKey(char)) {
                window[char] = window.getOrDefault(char, 0) + 1
                if (window[char] == need[char])
                    valid++
            }
            //移动左边界
            while (valid == need.size) {
                if (right - left < legth) {
                    start = left
                    legth = right - left
                }
                val char = s[left]
                left++
                if (need.containsKey(char)) {
                    if (window[char] == need[char])
                        valid--
                    window[char] = window.getOrDefault(char, 1) - 1
                }
            }
        }
        return if (legth == Int.MAX_VALUE) "" else s.substring(start, start + legth)
    }

    /**
     * 字符串排列
     * https://leetcode-cn.com/problems/permutation-in-string/
     */
    fun checkInclusion(s: String, t: String): Boolean {

        val need = HashMap<Char, Int>()
        val window = HashMap<Char, Int>()

        var left = 0
        var right = 0
        var valid = 0

        while (right < s.length) {
            val char = s[left];
            right++
            if (need.containsKey(char)) {
                window[char] = window.getOrDefault(char, 0)
                if (window[char] == need[char])
                    valid++
            }
            while (right - left < t.length) {
                if (valid == need.size) return true
                val char = s[left]
                left++
                if (need.containsKey(char)) {
                    if (window[char] == need[char]) valid--
                    window[char] = window.getOrDefault(char, 1) - 1
                }
            }
        }
        return false
    }

}