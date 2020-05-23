package com.example.tictacboom

import java.util.Arrays
import kotlin.collections.ArrayList
import java.util.*

class Cpu {

    fun miniMax(depth: Int, nodeIndex: Int, isMax: Boolean, scores: List<Int>, h: Int): Int{
        if(depth == h)
            return scores[nodeIndex]

        if(isMax)
            return max(miniMax(depth+1, nodeIndex*2, false, scores, h),
                miniMax(depth+1, nodeIndex*2 + 1, false, scores, h))
        else
            return min(miniMax(depth+1, nodeIndex*2, true, scores, h),
                miniMax(depth+1, nodeIndex*2 + 1, true, scores, h))
    }

    fun max(num_1: Int, num_2: Int): Int{
        return if (num_1 > num_2) num_1 else num_2 //max
    }

    fun min(num_1: Int, num_2: Int): Int{
        return if (num_1 < num_2) num_1 else num_2 //max
    }

    fun log2(n: Int): Int{
        println("+ 1")
        return if(n == 1) 0 else 1 + log2(n/2)
    }

    fun evaluate(board: Array<CharArray>): Int{

        // Checking for Riwsfor X or O victory.
        for(row in 0..2){
            if(board[row][0] == board[row][1] && board[row][1] == board[row][2]){
                if(board[row][0] == 'x')
                    return +10
                else if(board[row][0] == 'o')
                    return -10
            }//end of for loop block
        }

        // Checking for Columns for X or O victory.
        for(col in 0..2){
            if(board[0][col] == board[1][col] && board[1][col] == board[2][col]){
                if(board[0][col] == 'x')
                    return +10
                else if(board[0][col] == 'o')
                    return -10
            }//end of for loop block
        }

        // Checking for Diagonals for X or O victory.
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2])
        {
            if(board[0][0] == 'x')
                return +10
            else if(board[0][0] == 'o')
                return -10
        }
        if(board[0][2] == board[1][1] && board[1][1] == board[2][0])
        {
            if(board[0][2] == 'x')
                return +10
            else if(board[0][2] == 'o')
                return -10
        }

        // Else if none of them have won then return 0
        return 0
    }
}