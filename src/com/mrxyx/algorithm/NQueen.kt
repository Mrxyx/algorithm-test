package com.mrxyx.algorithm

import kotlin.math.abs

/**
 * n皇后
 */
class NQueen {

    //结果集
    private var result: MutableList<MutableList<String>> = mutableListOf()

    /**
     * 求解n皇后
     */
    fun getQueenResult(n: Int): MutableList<MutableList<String>> {
        val board: Array<CharArray> = Array(n) { CharArray(n) { '.' } }
        backTracing(board, 0, n)
        return result
    }

    private fun backTracing(board: Array<CharArray>, row: Int, n: Int) {
        if (row == n) {
            result.add(char2List(board))
            return
        }
        for (col in 0 until board[0].size) {
            if (valid(board, row, col)) {
                board[row][col] = 'Q'
                backTracing(board, row + 1, n)
                board[row][col] = '.'
            }
        }
    }

    /**
     * 是否满足放皇后条件
     */
    private fun valid(board: Array<CharArray>, row: Int, col: Int): Boolean {
        for (i in 0..row)
            for (j in 0 until board[0].size)
                if (board[i][j] == 'Q' && (col == j || abs(row - i) == abs(col - j))) return false
        return true
    }

    /**
     * 数组转listsum
     */
    private fun char2List(board: Array<CharArray>): MutableList<String> {
        val list: MutableList<String> = mutableListOf()
        for (i in board.indices)
            list.add(String(board[i]))
        return list
    }
}