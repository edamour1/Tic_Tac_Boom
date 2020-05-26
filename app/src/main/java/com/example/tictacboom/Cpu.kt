package com.example.tictacboom

import java.util.Arrays
import kotlin.collections.ArrayList
import java.util.*

class Cpu {
    class Move{
        var row: Int = 0
        var col: Int = 0
    }

    fun boardTranslator(bestMove: Move): Int{
        if(bestMove.row == 0 && bestMove.col == 0)
            return 1
        else if(bestMove.row == 0 && bestMove.col == 1)
            return 2
        else if(bestMove.row == 0 && bestMove.col == 2)
            return 3
        else if(bestMove.row == 1 && bestMove.col == 0)
            return 4
        else if(bestMove.row == 1 && bestMove.col == 1)
            return 5
        else if(bestMove.row == 1 && bestMove.col == 2)
            return 6
        else if(bestMove.row == 2 && bestMove.col == 0)
            return 7
        else if(bestMove.row == 2 && bestMove.col == 1)
            return 8
        else return 9

    }

    fun miniMax(board: Array<CharArray>, depth: Int, isMax: Boolean): Int{
        val player = 'o'
        val opponent = 'x'

        val score = evaluate(board)

        if(score == 10)
            return score

        if(score == -10)
            return -10

        if(isMovesLeft(board) == false)
            return 0

        if(isMax){
            var best = -1000

            //Traverse all cells
            for(row in 0..2){
                for(col in 0..2){

                    //Check if cell is empty
                    if(board[row][col] == '_'){

                        //Make the move
                        board[row][col] = player

                        //call minimax recursively and choose
                        //the maximum value
                        best = max(best, miniMax(board, depth + 1, !isMax))

                        //Undo move
                        board[row][col] = '_'
                    }//end of if block
                }//end of nested for loop block "col"
            }//end of for loop block "row"
            return best
        }//end of if block "isMax"
        else{
            var best = 1000

            //Traverse all cells
            for(row in 0..2){
                for(col in 0..2){

                    //Check if cell is empty
                    if(board[row][col] == '_'){

                        //Make he move
                        board[row][col] = opponent

                        //call minimax recursively and choose
                        //the maximum value
                        best = min(best, miniMax(board, depth + 1, !isMax))

                        //Undo move
                        board[row][col] = '_'
                    }//end of if block
                }//end of nested for loop block "col"
            }//end of for loop block "row"
            return best
        }

    }//end of miniMax function

    /*This will return the best possible
    **move for the player
    */
    fun findBestMove(board: Array<CharArray>): Move{
        val player = 'o'
        val opponent = 'x'
        var bestVal = -1000
        var bestMove = Move()
        bestMove.row = -1
        bestMove.col = -1

        for(row in 0..2){
            for(col in 0..2){
                if(board[row][col] == '_'){
                    //Make move
                    board[row][col] = player

                    // compute evaluation function for this
                    // move.
                    var moveVal = miniMax(board, 0, false)

                    //Undo the move
                    board[row][col] = '_'

                    // If the value of the current move is
                    // more than the best value, then update
                    // best
                    if (moveVal > bestVal)
                    {
                        bestMove.row = row;
                        bestMove.col = col;
                        bestVal = moveVal;
                    }//end of if block "moveVal > bestVal"

                }//end of if block "board == '_'"
            }//end of for loop block "col"
        }//end of for loop block "row"

        return bestMove
    }//end of "findBestMove" function

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
                if(board[row][0] == 'o')
                    return +10
                else if(board[row][0] == 'x')
                    return -10
            }//end of for loop block
        }

        // Checking for Columns for X or O victory.
        for(col in 0..2){
            if(board[0][col] == board[1][col] && board[1][col] == board[2][col]){
                if(board[0][col] == 'o')
                    return +10
                else if(board[0][col] == 'x')
                    return -10
            }//end of for loop block
        }

        // Checking for Diagonals for X or O victory.
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2])
        {
            if(board[0][0] == 'o')
                return +10
            else if(board[0][0] == 'x')
                return -10
        }
        if(board[0][2] == board[1][1] && board[1][1] == board[2][0])
        {
            if(board[0][2] == 'o')
                return +10
            else if(board[0][2] == 'x')
                return -10
        }

        // Else if none of them have won then return 0
        return 0
    }

    fun isMovesLeft(board: Array<CharArray>): Boolean{
        for(row in 0..2){
            for(col in 0..2){
                if(board[row][col] == '_')
                    return true
            }//end of for loop block
        }//end of for loop block

        return false
    }
}