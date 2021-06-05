package com.mrxyx.algorithm

/**
 * 二分查找
 */
class BinarySearch {

    /**
     * 二分查找
     * https://leetcode-cn.com/problems/binary-search/
     */
    fun binarySearch(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return -1
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            when {
                nums[mid] == target -> return mid
                nums[mid] < target -> left = mid + 1
                nums[mid] > target -> right = mid - 1
            }
        }
        return -1
    }

    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
     */
    fun searchRange(nums: IntArray, target: Int): IntArray {
        return intArrayOf(leftBound(nums, target), rightBound(nums, target))
    }

    private fun leftBound(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return -1
        var left = 0
        var right = nums.size - 1
        var mid: Int
        while (left <= right) {
            mid = left + (right - left) / 2
            when {
                nums[mid] > target || nums[mid] == target -> right = mid - 1
                nums[mid] < target -> left = mid + 1
            }
        }
        return left
    }

    private fun rightBound(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return -1
        var left = 0
        var right = nums.size - 1
        var mid: Int
        while (left <= right) {
            mid = left + (right - left) / 2
            when {
                nums[mid] < target -> left = mid + 1
                nums[mid] > target -> right = mid - 1
                nums[mid] == target -> left = mid + 1
            }
        }
        return right
    }
}