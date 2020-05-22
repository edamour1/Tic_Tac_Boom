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
}