package com.example.tictacboom

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
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
    var setPlayer = 2
    var bomb: Int = 1
    val cpu = Cpu()
    var CheckWinner = {
        println("in CheckWinner() function \n")
        var winner = -1
        //row1
        if (player_1.moves.contains(1) && player_1.moves.contains(2) && player_1.moves.contains(3))
        {
            winner = 1
            println("player_1.moves.contains(1) && player_1.moves.contains(2) && player_1.moves.contains(3) \n")
        }
        if (player_2.moves.contains(1) && player_2.moves.contains(2) && player_2.moves.contains(3))
        {
            winner = 2
            println("player_2.moves.contains(1) && player_2.moves.contains(2) && player_2.moves.contains(3) \n")
        }
        //row2
        if (player_1.moves.contains(4) && player_1.moves.contains(5) && player_1.moves.contains(6))
        {
            winner = 1
            println("player_1.moves.contains(4) && player_1.moves.contains(5) && player_1.moves.contains(6) \n")
        }
        if (player_2.moves.contains(4) && player_2.moves.contains(5) && player_2.moves.contains(6))
        {
            winner = 2
            println("player_2.moves.contains(4) && player_2.moves.contains(5) && player_2.moves.contains(6) \n")
        }
        //row3
        if (player_1.moves.contains(7) && player_1.moves.contains(8) && player_1.moves.contains(9))
        {
            winner = 1
            println("player_1.moves.contains(7) && player_1.moves.contains(8) && player_1.moves.contains(9) \n")
        }
        if (player_2.moves.contains(7) && player_2.moves.contains(8) && player_2.moves.contains(9))
        {
            winner = 2
            println("player_2.moves.contains(7) && player_2.moves.contains(8) && player_2.moves.contains(9) \n")
        }
        //col1
        if (player_1.moves.contains(1) && player_1.moves.contains(4) && player_1.moves.contains(7))
        {
            winner = 1
            println("player_1.moves.contains(1) && player_1.moves.contains(4) && player_1.moves.contains(7) \n")
        }
        if (player_2.moves.contains(1) && player_2.moves.contains(4) && player_2.moves.contains(7))
        {
            winner = 2
            println("player_2.moves.contains(1) && player_2.moves.contains(4) && player_2.moves.contains(7) \n")
        }
        //col2
        if (player_1.moves.contains(2) && player_1.moves.contains(5) && player_1.moves.contains(8))
        {
            winner = 1
            println("player_1.moves.contains(2) && player_1.moves.contains(5) && player_1.moves.contains(8) \n")
        }
        if (player_2.moves.contains(2) && player_2.moves.contains(5) && player_2.moves.contains(8))
        {
            winner = 2
            println("player_2.moves.contains(2) && player_2.moves.contains(5) && player_2.moves.contains(8) \n")
        }
        //col3
        if (player_1.moves.contains(3) && player_1.moves.contains(6) && player_1.moves.contains(9))
        {
            winner = 1
            println("player_1.moves.contains(3) && player_1.moves.contains(6) && player_1.moves.contains(9) \n")
        }
        if (player_2.moves.contains(3) && player_2.moves.contains(6) && player_2.moves.contains(9))
        {
            winner = 2
            println("player_2.moves.contains(3) && player_2.moves.contains(6) && player_2.moves.contains(9) \n")
        }
        //cross1
        if (player_1.moves.contains(1) && player_1.moves.contains(5) && player_1.moves.contains(9))
        {
            winner = 1
            println("player_1.moves.contains(1) && player_1.moves.contains(5) && player_1.moves.contains(9) \n")
        }
        if (player_2.moves.contains(1) && player_2.moves.contains(5) && player_2.moves.contains(9))
        {
            winner = 2
            println("player_2.moves.contains(1) && player_2.moves.contains(5) && player_2.moves.contains(9) \n")
        }
        //cross2
        if (player_1.moves.contains(3) && player_1.moves.contains(5) && player_1.moves.contains(7))
        {
            winner = 1
            println("player_1.moves.contains(3) && player_1.moves.contains(5) && player_1.moves.contains(7) \n")
        }
        if (player_2.moves.contains(3) && player_2.moves.contains(5) && player_2.moves.contains(7))
        {
            winner = 2
            println("player_2.moves.contains(3) && player_2.moves.contains(5) && player_2.moves.contains(7) \n")
        }

        if (winner != -1)
        {
            println("there is a winner \n")
            if (winner == 1)
            {
                println("if (winner == 1) \n")
                if(setPlayer == 1) {
                    println("if(setPlayer == 1) \n")
                    Toast.makeText(this, "Player 1 Wins!!", Toast.LENGTH_SHORT).show()
                    println("player_1 wins \n")
                    stopTouch()
                }
                else
                {
                    println("setPlayer not 1 \n")
                    Toast.makeText(this, "You Won!!", Toast.LENGTH_SHORT).show()
                    println("You Won!! \n")
                    stopTouch()
                }
            }
            else
            {
                println("winner is -1) \n")
                if (setPlayer == 1) {
                    println("if(setPlayer == 1) \n")
                    Toast.makeText(this, "Player 2 Wins!!", Toast.LENGTH_SHORT).show()
                    println("player 2 wins \n")
                    stopTouch()
                }
                else
                {
                    println("else set player was not 1 \n")
                    Toast.makeText(this, "CPU Wins!!", Toast.LENGTH_SHORT).show()
                    println("CPU Wins \n")
                    stopTouch()
                }
            }
        }
    }

    var animationDrawable_1 = AnimationDrawable()
    var animationDrawable_2 = AnimationDrawable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflate()
    }

    private fun inflate() {
        player_1.src = R.drawable.neon_blue_x
        player_1.srcSetAnimation = R.drawable.x_animation
        player_2.src = R.drawable.neon_red_o
        player_2.srcSetAnimation = R.drawable.heart_animation
        placeBomb()
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
        setPlayer = 2

        PVP.setBackgroundColor(android.R.drawable.btn_default)
        PVC.setBackgroundColor(Color.CYAN)
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
        playGame(cellId,buSelected)
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

    fun playGame(cellId:Int,buSelected:ImageButton)
    {
        println("in playGame(cellId:Int,buSelected:ImageButton) function")
        if (ActivePlayer == 1)
        {
            println("player_1 play")
            buSelected.setImageResource(player_1.src)

            player_1.moves.add(cellId)
            breakFunction(cellId,buSelected,CheckWinner)
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
            println("player_2 play")
            buSelected.setImageResource(player_2.src)

            player_2.moves.add(cellId)
            ActivePlayer = 1
            breakFunction(cellId,buSelected,CheckWinner)
        }//end of else block
    }

    fun CheckWinner()
    {
        println("in CheckWinner() function \n")
        var winner = -1
        //row1
        if (player_1.moves.contains(1) && player_1.moves.contains(2) && player_1.moves.contains(3))
        {
            winner = 1
            println("player_1.moves.contains(1) && player_1.moves.contains(2) && player_1.moves.contains(3) \n")
        }
        if (player_2.moves.contains(1) && player_2.moves.contains(2) && player_2.moves.contains(3))
        {
            winner = 2
            println("player_2.moves.contains(1) && player_2.moves.contains(2) && player_2.moves.contains(3) \n")
        }
        //row2
        if (player_1.moves.contains(4) && player_1.moves.contains(5) && player_1.moves.contains(6))
        {
            winner = 1
            println("player_1.moves.contains(4) && player_1.moves.contains(5) && player_1.moves.contains(6) \n")
        }
        if (player_2.moves.contains(4) && player_2.moves.contains(5) && player_2.moves.contains(6))
        {
            winner = 2
            println("player_2.moves.contains(4) && player_2.moves.contains(5) && player_2.moves.contains(6) \n")
        }
        //row3
        if (player_1.moves.contains(7) && player_1.moves.contains(8) && player_1.moves.contains(9))
        {
            winner = 1
            println("player_1.moves.contains(7) && player_1.moves.contains(8) && player_1.moves.contains(9) \n")
        }
        if (player_2.moves.contains(7) && player_2.moves.contains(8) && player_2.moves.contains(9))
        {
            winner = 2
            println("player_2.moves.contains(7) && player_2.moves.contains(8) && player_2.moves.contains(9) \n")
        }
        //col1
        if (player_1.moves.contains(1) && player_1.moves.contains(4) && player_1.moves.contains(7))
        {
            winner = 1
            println("player_1.moves.contains(1) && player_1.moves.contains(4) && player_1.moves.contains(7) \n")
        }
        if (player_2.moves.contains(1) && player_2.moves.contains(4) && player_2.moves.contains(7))
        {
            winner = 2
            println("player_2.moves.contains(1) && player_2.moves.contains(4) && player_2.moves.contains(7) \n")
        }
        //col2
        if (player_1.moves.contains(2) && player_1.moves.contains(5) && player_1.moves.contains(8))
        {
            winner = 1
            println("player_1.moves.contains(2) && player_1.moves.contains(5) && player_1.moves.contains(8) \n")
        }
        if (player_2.moves.contains(2) && player_2.moves.contains(5) && player_2.moves.contains(8))
        {
            winner = 2
            println("player_2.moves.contains(2) && player_2.moves.contains(5) && player_2.moves.contains(8) \n")
        }
        //col3
        if (player_1.moves.contains(3) && player_1.moves.contains(6) && player_1.moves.contains(9))
        {
            winner = 1
            println("player_1.moves.contains(3) && player_1.moves.contains(6) && player_1.moves.contains(9) \n")
        }
        if (player_2.moves.contains(3) && player_2.moves.contains(6) && player_2.moves.contains(9))
        {
            winner = 2
            println("player_2.moves.contains(3) && player_2.moves.contains(6) && player_2.moves.contains(9) \n")
        }
        //cross1
        if (player_1.moves.contains(1) && player_1.moves.contains(5) && player_1.moves.contains(9))
        {
            winner = 1
            println("player_1.moves.contains(1) && player_1.moves.contains(5) && player_1.moves.contains(9) \n")
        }
        if (player_2.moves.contains(1) && player_2.moves.contains(5) && player_2.moves.contains(9))
        {
            winner = 2
            println("player_2.moves.contains(1) && player_2.moves.contains(5) && player_2.moves.contains(9) \n")
        }
        //cross2
        if (player_1.moves.contains(3) && player_1.moves.contains(5) && player_1.moves.contains(7))
        {
            winner = 1
            println("player_1.moves.contains(3) && player_1.moves.contains(5) && player_1.moves.contains(7) \n")
        }
        if (player_2.moves.contains(3) && player_2.moves.contains(5) && player_2.moves.contains(7))
        {
            winner = 2
            println("player_2.moves.contains(3) && player_2.moves.contains(5) && player_2.moves.contains(7) \n")
        }

        if (winner != -1)
        {
            println("there is a winner \n")
            if (winner == 1)
            {
                println("if (winner == 1) \n")
                if(setPlayer == 1) {
                    println("if(setPlayer == 1) \n")
                    Toast.makeText(this, "Player 1 Wins!! SIKE", Toast.LENGTH_SHORT).show()
                    println("player_1 wins \n")
                    stopTouch()
                }
                else
                {
                    println("setPlayer not 1 \n")
                    Toast.makeText(this, "You Won!! SIKE", Toast.LENGTH_SHORT).show()
                    println("You Won!! \n")
                    stopTouch()
                }
            }
            else
            {
                println("winner is -1) \n")
                if (setPlayer == 1) {
                    println("if(setPlayer == 1) \n")
                    Toast.makeText(this, "Player 2 Wins!! SIKE", Toast.LENGTH_SHORT).show()
                    println("player 2 wins \n")
                    stopTouch()
                }
                else
                {
                    println("else set player was not 1 \n")
                    Toast.makeText(this, "CPU Wins!! SIKE", Toast.LENGTH_SHORT).show()
                    println("CPU Wins \n")
                    stopTouch()
                }
            }
        }
    }

    fun stopTouch()
    {
        println("in stopTouch() function \n")
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
        println("In AutoPlay() function \n")

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

//        if(cellId == bomb){removePiece(cellId, buSelect)}
        playGame(cellId,buSelect)
        boardBuilder()
    }

    /***************************************************
     ***************************************************
     **This function places a random bomb on the board**
     ***************************************************
     ****************************************************/
    fun placeBomb(){
        println("In placeBomb function \n")
        val r = Random()
        bomb = r.nextInt(9)+1
        val removeAtIndex: Int
        println("Bomb set: $bomb")
        if(player_1.moves.contains(bomb)){
            println("In placeBomb function player_1.moves contains bomb \n")
            println("In placeBomb function before remove player_1 moves ${player_1.moves.toString()} \n")
            removeAtIndex = player_1.moves.indexOf(bomb)
            player_1.moves.removeAt(removeAtIndex)
            println("In placeBomb function after remove player_1 moves ${player_1.moves.toString()} \n")
            sweepBoard(bomb)
        }else if(player_2.moves.contains(bomb)){
            println("In placeBomb function player_2.moves contains bomb \n")
            println("In placeBomb function before remove player_2 moves ${player_2.moves.toString()} \n")
            removeAtIndex = player_2.moves.indexOf(bomb)
            player_2.moves.removeAt(removeAtIndex)
            println("In placeBomb function after remove player_2 moves ${player_2.moves.toString()} \n")
            sweepBoard(bomb)
        }//end of else if statement
    }

    fun breakFunction(cellId:Int, buSelected:ImageButton, CheckWinner: () -> Unit){
        println("in breakFunction before checking for bombs")
        buSelected.isEnabled = false
        println("in playGame function before checking for bombs")
        println("button ${buSelected.id} enabled = ${buSelected.isEnabled} \n")
        if(cellId == bomb){removePiece(cellId, buSelected)
        }else{CheckWinner()}
        println("in playGame function after checking for bombs")
        println("button ${buSelected.id} enabled = ${buSelected.isEnabled} \n")
        boardBuilder()
    }

    fun removePiece(cellId:Int, buSelected:ImageButton){
        println("In removePiece function \n")
        val removeAtIndex: Int
        print("\n")
        if(player_1.moves.contains(bomb)){
            buSelected.setImageResource(R.drawable.deafualt_button)
            println("Player_1 contains bomb")
            println("bomb is = $bomb \n")
            println(player_1.moves.toString())
            removeAtIndex = player_1.moves.indexOf(bomb)
            println("removeAtIndex is $removeAtIndex")
            println("New player_1.moves index \n")
            player_1.moves.removeAt(removeAtIndex)
            println(player_1.moves.toString())
        }else if(player_2.moves.contains(bomb)){
            buSelected.setImageResource(R.drawable.deafualt_button)
            println("Player_2 contains bomb")
            println("bomb is = $bomb \n")
            println(player_2.moves.toString())
            removeAtIndex = player_2.moves.indexOf(bomb)
            println("removeAtIndex is $removeAtIndex")
            println("New player_2.moves index \n")
            player_2.moves.removeAt(removeAtIndex)
            println(player_2.moves.toString())
        }

        println("in remove function before button re enabled ${buSelected.isEnabled}")
        buSelected.isEnabled = true
        println("in remove function The button ${buSelected.id} after button is enabled ${buSelected.isEnabled}")
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

        println("sweep function before button re enabled ${buSelect.id} is enabled = ${buSelect.isEnabled}")
        buSelect.setImageResource(R.drawable.deafualt_button)
        buSelect.isEnabled = true
        println("sweep function after but before place bomb button re enabled ${buSelect.id} is enabled = ${buSelect.isEnabled}")
        placeBomb()
        println("sweep function after button re enabled ${buSelect.id} is enabled = ${buSelect.isEnabled}")
        boardBuilder()
        println("exit sweep function")
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

    fun AnimationDrawable.onAnimationFinished(block: () -> Unit) {
        var duration: Long = 0
        for (i in 0..numberOfFrames) {
            duration += getDuration(i)
        }
        Handler().postDelayed({
            block()
        }, duration + 200)
    }

}