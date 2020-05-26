package com.example.tictacboom

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var player_1 = Player()
    var player_2 = Player()

    var  CPU_List = ArrayList<Int>()

    var ActivePlayer = 1
    var setPlayer = 1
    var bomb: Int = 1
    val cpu = Cpu()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflate()
    }

    private fun inflate() {
        player_1.src = R.drawable.neon_blue_x
        player_2.src = R.drawable.neon_red_o
    }

    fun restartGame(view: View)
    {
        placeBomb()
        button1.setImageResource(R.drawable.deafualt_button)
        button2.setImageResource(R.drawable.deafualt_button)
        button3.setImageResource(R.drawable.deafualt_button)
        button4.setImageResource(R.drawable.deafualt_button)
        button5.setImageResource(R.drawable.deafualt_button)
        button6.setImageResource(R.drawable.deafualt_button)
        button7.setImageResource(R.drawable.deafualt_button)
        button8.setImageResource(R.drawable.deafualt_button)
        button9.setImageResource(R.drawable.deafualt_button)

        player_1.moves.clear()
        player_2.moves.clear()
        CPU_List.clear()
        ActivePlayer = 1
        button1.isEnabled = true
        button2.isEnabled = true
        button3.isEnabled = true
        button4.isEnabled = true
        button5.isEnabled = true
        button6.isEnabled = true
        button7.isEnabled = true
        button8.isEnabled = true
        button9.isEnabled = true
        setPlayer = 1
        PVP.setBackgroundColor(Color.CYAN)
        PVC.setBackgroundColor(android.R.drawable.btn_default)
    }
    fun buttonClick(view: View)
    {
        val buSelected: ImageButton = view as ImageButton
        var cellId = 0
        when(buSelected.id)
        {
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
        }
        PlayGame(cellId,buSelected)
    }
    fun PlayerChoose(view:View)
    {
        val ps:Button = view as Button
        when(ps.id)
        {
            R.id.PVP -> {
                setPlayer = 1
                ps.setBackgroundColor(Color.CYAN)
                PVC.setBackgroundColor(android.R.drawable.btn_default)
            }
            R.id.PVC -> {
                setPlayer = 2
                ps.setBackgroundColor(Color.CYAN)
                PVP.setBackgroundColor(android.R.drawable.btn_default)
            }
        }
    }

    fun PlayGame(cellId:Int,buSelected:ImageButton)
    {
        if (ActivePlayer == 1)
        {
            buSelected.setImageResource(player_1.src)
            buSelected.setBackgroundColor(Color.GREEN)
            player_1.moves.add(cellId)


            ActivePlayer = 2
            if (setPlayer == 1)
            {}
            else
            {
                try {
                    AutoPlay()
                }catch (ex:Exception)
                {
                    Toast.makeText(this,"Game Over",Toast.LENGTH_SHORT).show()
                }//end of catch block
            }//end of else block
        }//end of if block "ActivePlayer == 1"
        else
        {
            buSelected.setImageResource(player_2.src)
            buSelected.setBackgroundColor(Color.CYAN)
            player_2.moves.add(cellId)
            ActivePlayer = 1
        }
        buSelected.isEnabled = false
        println("The button ${buSelected.id} is enabled ${buSelected.isEnabled}")

        if(cellId == bomb){removePiece(cellId, buSelected)}
        boardBuilder()
        CheckWinner()
    }

    fun CheckWinner()
    {
        var winner = -1
        //row1
        if (player_1.moves.contains(1) && player_1.moves.contains(2) && player_1.moves.contains(3))
        {
            winner = 1
        }
        if (player_2.moves.contains(1) && player_2.moves.contains(2) && player_2.moves.contains(3))
        {
            winner = 2
        }
        //row2
        if (player_1.moves.contains(4) && player_1.moves.contains(5) && player_1.moves.contains(6))
        {
            winner = 1
        }
        if (player_2.moves.contains(4) && player_2.moves.contains(5) && player_2.moves.contains(6))
        {
            winner = 2
        }
        //row3
        if (player_1.moves.contains(7) && player_1.moves.contains(8) && player_1.moves.contains(9))
        {
            winner = 1
        }
        if (player_2.moves.contains(7) && player_2.moves.contains(8) && player_2.moves.contains(9))
        {
            winner = 2
        }
        //col1
        if (player_1.moves.contains(1) && player_1.moves.contains(4) && player_1.moves.contains(7))
        {
            winner = 1
        }
        if (player_2.moves.contains(1) && player_2.moves.contains(4) && player_2.moves.contains(7))
        {
            winner = 2
        }
        //col2
        if (player_1.moves.contains(2) && player_1.moves.contains(5) && player_1.moves.contains(8))
        {
            winner = 1
        }
        if (player_2.moves.contains(2) && player_2.moves.contains(5) && player_2.moves.contains(8))
        {
            winner = 2
        }
        //col3
        if (player_1.moves.contains(3) && player_1.moves.contains(6) && player_1.moves.contains(9))
        {
            winner = 1
        }
        if (player_2.moves.contains(3) && player_2.moves.contains(6) && player_2.moves.contains(9))
        {
            winner = 2
        }
        //cross1
        if (player_1.moves.contains(1) && player_1.moves.contains(5) && player_1.moves.contains(9))
        {
            winner = 1
        }
        if (player_2.moves.contains(1) && player_2.moves.contains(5) && player_2.moves.contains(9))
        {
            winner = 2
        }
        //cross2
        if (player_1.moves.contains(3) && player_1.moves.contains(5) && player_1.moves.contains(7))
        {
            winner = 1
        }
        if (player_2.moves.contains(3) && player_2.moves.contains(5) && player_2.moves.contains(7))
        {
            winner = 2
        }
        if (winner != -1)
        {
            if (winner == 1)
            {
                if(setPlayer == 1) {
                    Toast.makeText(this, "Player 1 Wins!!", Toast.LENGTH_SHORT).show()
                    stopTouch()
                }
                else
                {
                    Toast.makeText(this, "You Won!!", Toast.LENGTH_SHORT).show()
                    stopTouch()
                }
            }
            else
            {
                if (setPlayer == 1) {
                    Toast.makeText(this, "Player 2 Wins!!", Toast.LENGTH_SHORT).show()
                    stopTouch()
                }
                else
                {
                    Toast.makeText(this, "CPU Wins!!", Toast.LENGTH_SHORT).show()
                    stopTouch()
                }
            }
        }
    }

    fun stopTouch()
    {
        button1.isEnabled = false
        button2.isEnabled = false
        button3.isEnabled = false
        button4.isEnabled = false
        button5.isEnabled = false
        button6.isEnabled = false
        button7.isEnabled = false
        button8.isEnabled = false
        button9.isEnabled = false
    }

    fun AutoPlay()
    {
        //create ArrayList to keep track of available spots that are on the board
        val emptyCells = ArrayList<Int>()

        for (cellId in 1..9) {//cellId is alias for values that are looped from 1 - 9
            if (!player_1.moves.contains(cellId) && !player_2.moves.contains(cellId))//if current value isn't occupied by player 1 and 2 add value to "emptyCells" list
            { emptyCells.add(cellId) }//end of if staement
        }//end of forloop


//        val r = Random()
//        val randomIndex = r.nextInt(emptyCells.size-0)+0
//        val cellId = emptyCells[randomIndex]
        val buSelect:ImageButton?
        //CPU_List.add(cellId)
        val cellId = cpu.boardTranslator(cpu.findBestMove(boardBuilder()))

        when(cellId)
        {
            1 -> buSelect = button1
            2 -> buSelect = button2
            3 -> buSelect = button3
            4 -> buSelect = button4
            5 -> buSelect = button5
            6 -> buSelect = button6
            7 -> buSelect = button7
            8 -> buSelect = button8
            9 -> buSelect = button9
            else -> buSelect = button1
        }//end of when block "cellId"
        if(cellId == bomb){removePiece(cellId, buSelect)}
        PlayGame(cellId,buSelect)
        boardBuilder()
    }

    /**************************************************
     ***************************************************
     **This function places a random bomb on the board**
     ***************************************************
     ****************************************************/
    fun placeBomb(){
        println("In placeBomb function")
        val r = Random()
        bomb = r.nextInt(9)+1
        val removeAtIndex: Int

        if(player_1.moves.contains(bomb)){
            println("In placeBomb function player_1.moves bomb")
            removeAtIndex = player_1.moves.indexOf(bomb)
            player_1.moves.removeAt(removeAtIndex)
            sweepBoard(bomb)
        }else if(player_2.moves.contains(bomb)){
            println("In placeBomb function player_2.moves bomb")
            removeAtIndex = player_2.moves.indexOf(bomb)
            player_2.moves.removeAt(removeAtIndex)
            sweepBoard(bomb)
        }else if(CPU_List.contains(bomb)){
            println("In placeBomb function CPU bomb")
            removeAtIndex = CPU_List.indexOf(bomb)
            CPU_List.removeAt(removeAtIndex)
            boardBuilder()
            sweepBoard(bomb)
        }//end of else if statement
    }

    fun removePiece(cellId:Int, buSelected:ImageButton){
        println("In removePiece function")
        val removeAtIndex: Int
        print("\n")
        if(player_1.moves.contains(bomb)){
            buSelected.setImageResource(R.drawable.deafualt_button)
            println("Player_1 contains bomb")
            println("bomb is = $bomb")
            println(player_1.moves.toString())
            removeAtIndex = player_1.moves.indexOf(bomb)
            println("removeAtIndex is $removeAtIndex")
            println("New player_1.moves index")
            player_1.moves.removeAt(removeAtIndex)
            println(player_1.moves.toString())
        }else if(player_2.moves.contains(bomb)){
            buSelected.setImageResource(R.drawable.deafualt_button)
            println("Player_2 contains bomb")
            println("bomb is = $bomb")
            println(player_2.moves.toString())
            removeAtIndex = player_2.moves.indexOf(bomb)
            println("removeAtIndex is $removeAtIndex")
            println("New player_2.moves index")
            player_2.moves.removeAt(removeAtIndex)
            println(player_2.moves.toString())
        }else if(CPU_List.contains(bomb)){
            buSelected.setImageResource(R.drawable.deafualt_button)
            println("CPU_List contains bomb")
            println("bomb is = $bomb")
            println(CPU_List.toString())
            removeAtIndex = CPU_List.indexOf(bomb)
            println("removeAtIndex is $removeAtIndex")
            println("New CPU_List index")
            CPU_List.removeAt(removeAtIndex)
            println(CPU_List.toString())
            boardBuilder()
        }//end of else if blcok "CPU_List.contains(bomb)"

        buSelected.isEnabled = true
        println("The button ${buSelected.id} is enabled ${buSelected.isEnabled}")
        placeBomb()
    }

    fun sweepBoard(cellId: Int){
        println("In Sweep function")
        val buSelect:ImageButton?
        when(cellId)
        {
            1 -> buSelect = button1
            2 -> buSelect = button2
            3 -> buSelect = button3
            4 -> buSelect = button4
            5 -> buSelect = button5
            6 -> buSelect = button6
            7 -> buSelect = button7
            8 -> buSelect = button8
            9 -> buSelect = button9
            else -> buSelect = button1
        }

        buSelect.setImageResource(R.drawable.deafualt_button)
        buSelect.isEnabled = true
        placeBomb()
        boardBuilder()
    }

    fun boardBuilder(): Array<CharArray>{
        println("In board function")
        val board = arrayOf(
            charArrayOf('_', '_','_'),
            charArrayOf('_', '_', '_'),
            charArrayOf('_', '_', '_')
        )
        for(cellId in player_1.moves){
            when(cellId)
            {
                1 -> board[0][0] = 'x'
                2 -> board[0][1] = 'x'
                3 -> board[0][2] = 'x'
                4 -> board[1][0] = 'x'
                5 -> board[1][1] = 'x'
                6 -> board[1][2] = 'x'
                7 -> board[2][0] = 'x'
                8 -> board[2][1] = 'x'
                else -> board[2][2] = 'x'
            }//end of when block "cellId"
        }//end of forloop block "cellId in player_1.moves"

        for(cellId in player_2.moves){
            when(cellId)
            {
                1 -> board[0][0] = 'o'
                2 -> board[0][1] = 'o'
                3 -> board[0][2] = 'o'
                4 -> board[1][0] = 'o'
                5 -> board[1][1] = 'o'
                6 -> board[1][2] = 'o'
                7 -> board[2][0] = 'o'
                8 -> board[2][1] = 'o'
                else -> board[2][2] = 'o'
            }//end of when block "cellId"
        }//end of forloop block "cellId in player_1.moves"

        println("player_2.moves Array")
        println(player_2.moves.toString())
        println()
        println("moves Array")
        println(player_1.moves.toString())
        println(Arrays.deepToString(board))

        return board

    }//end of "boardBuilder" function

}