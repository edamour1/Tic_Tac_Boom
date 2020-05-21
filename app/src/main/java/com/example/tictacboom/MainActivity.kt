package com.example.tictacboom

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var Player1 = ArrayList<Int>()
    var Player2 = ArrayList<Int>()
    var ActivePlayer = 1
    var setPlayer = 1
    var bomb: Int = 1

    fun restartGame(view: View)
    {
        placeBomb()
        button1.setBackgroundResource(android.R.drawable.btn_default)
        button2.setBackgroundResource(android.R.drawable.btn_default)
        button3.setBackgroundResource(android.R.drawable.btn_default)
        button4.setBackgroundResource(android.R.drawable.btn_default)
        button5.setBackgroundResource(android.R.drawable.btn_default)
        button6.setBackgroundResource(android.R.drawable.btn_default)
        button7.setBackgroundResource(android.R.drawable.btn_default)
        button8.setBackgroundResource(android.R.drawable.btn_default)
        button9.setBackgroundResource(android.R.drawable.btn_default)
        button1.text = ""
        button2.text = ""
        button3.text = ""
        button4.text = ""
        button5.text = ""
        button6.text = ""
        button7.text = ""
        button8.text = ""
        button9.text = ""
        Player1.clear()
        Player2.clear()
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
        val buSelected: Button = view as Button
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
    fun PlayGame(cellId:Int,buSelected:Button)
    {
        if (ActivePlayer == 1)
        {
            buSelected.text = "X"
            buSelected.setBackgroundColor(Color.GREEN)
            Player1.add(cellId)

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
                }
            }
        }
        else
        {
            buSelected.text = "O"
            buSelected.setBackgroundColor(Color.CYAN)
            Player2.add(cellId)
            ActivePlayer = 1
            //removePiece(cellId, buSelected)
        }
        buSelected.isEnabled = false
        println("The button ${buSelected.id} is enabled ${buSelected.isEnabled}")

        if(cellId == bomb){removePiece(cellId, buSelected)}

        CheckWinner()
    }

    fun CheckWinner()
    {
        var winner = -1
        //row1
        if (Player1.contains(1) && Player1.contains(2) && Player1.contains(3))
        {
            winner = 1
        }
        if (Player2.contains(1) && Player2.contains(2) && Player2.contains(3))
        {
            winner = 2
        }
        //row2
        if (Player1.contains(4) && Player1.contains(5) && Player1.contains(6))
        {
            winner = 1
        }
        if (Player2.contains(4) && Player2.contains(5) && Player2.contains(6))
        {
            winner = 2
        }
        //row3
        if (Player1.contains(7) && Player1.contains(8) && Player1.contains(9))
        {
            winner = 1
        }
        if (Player2.contains(7) && Player2.contains(8) && Player2.contains(9))
        {
            winner = 2
        }
        //col1
        if (Player1.contains(1) && Player1.contains(4) && Player1.contains(7))
        {
            winner = 1
        }
        if (Player2.contains(1) && Player2.contains(4) && Player2.contains(7))
        {
            winner = 2
        }
        //col2
        if (Player1.contains(2) && Player1.contains(5) && Player1.contains(8))
        {
            winner = 1
        }
        if (Player2.contains(2) && Player2.contains(5) && Player2.contains(8))
        {
            winner = 2
        }
        //col3
        if (Player1.contains(3) && Player1.contains(6) && Player1.contains(9))
        {
            winner = 1
        }
        if (Player2.contains(3) && Player2.contains(6) && Player2.contains(9))
        {
            winner = 2
        }
        //cross1
        if (Player1.contains(1) && Player1.contains(5) && Player1.contains(9))
        {
            winner = 1
        }
        if (Player2.contains(1) && Player2.contains(5) && Player2.contains(9))
        {
            winner = 2
        }
        //cross2
        if (Player1.contains(3) && Player1.contains(5) && Player1.contains(7))
        {
            winner = 1
        }
        if (Player2.contains(3) && Player2.contains(5) && Player2.contains(7))
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
            if (!Player1.contains(cellId) && !Player2.contains(cellId))//if current value isn't occupied by player 1 and 2 add value to "emptyCells" list
            { emptyCells.add(cellId) }//end of if staement
        }//end of forloop

        val r = Random()
        val randomIndex = r.nextInt(emptyCells.size-0)+0
        val cellId = emptyCells[randomIndex]
        val buSelect:Button?
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

        PlayGame(cellId,buSelect)
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

        if(Player1.contains(bomb)){
            println("In placeBomb function Player1 bomb")
            removeAtIndex = Player1.indexOf(bomb)
            Player1.removeAt(removeAtIndex)
            sweepBoard(bomb)
        }else if(Player2.contains(bomb)){
            println("In placeBomb function Player2 bomb")
            removeAtIndex = Player2.indexOf(bomb)
            Player2.removeAt(removeAtIndex)
            sweepBoard(bomb)
        }//end of else if statement

    }

    fun removePiece(cellId:Int, buSelected:Button){
        println("In removePiece function")
        val removeAtIndex: Int
            print("\n")
        if(Player1.contains(bomb)){
            buSelected.text = "boom"
            println("Player_1 contains bomb")
            println("bomb is = $bomb")
            println(Player1.toString())
            removeAtIndex = Player1.indexOf(bomb)
            println("removeAtIndex is $removeAtIndex")
            println("New Player1 index")
            Player1.removeAt(removeAtIndex)
            println(Player1.toString())
        }else if(Player2.contains(bomb)){
            buSelected.text = "boom"
            println("Player_2 contains bomb")
            println("bomb is = $bomb")
            println(Player2.toString())
            removeAtIndex = Player2.indexOf(bomb)
            println("removeAtIndex is $removeAtIndex")
            println("New Player2 index")
            Player2.removeAt(removeAtIndex)
            println(Player2.toString())
        }//end of else if block

        buSelected.isEnabled = true
        println("The button ${buSelected.id} is enabled ${buSelected.isEnabled}")
        placeBomb()
    }

    fun sweepBoard(cellId: Int){
        println("In Sweep function")
        val buSelect:Button?
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

        buSelect.text = "boom"
        buSelect.isEnabled = true
        placeBomb()

    }

}