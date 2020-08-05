package com.example.tictacboom

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.ImageButton
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.media.AudioManager
import android.media.SoundPool
import kotlinx.android.synthetic.main.activity_play_game.*
import java.util.*
import kotlin.collections.ArrayList

class PlayGameActivity : AppCompatActivity() {

    var player_1 = Player()
    var player_2 = Player()
    var isGameOver = false
    var  CPU_List = ArrayList<Int>()

    var ActivePlayer = 1
    var setPlayer = 2
    var bomb: Int = 1
    val cpu = Cpu()

    var animationDrawable_1 = AnimationDrawable()
    var animationDrawable_2 = AnimationDrawable()
    var animationDrawable_3 = AnimationDrawable()

    private var soundPool: SoundPool? = null
    private var soundPool_set_sound: SoundPool? = null

    private val soundId = 1
    private val soundId_2 = 2
    private val soundId_3 = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_game_nav)
        setSupportActionBar(toolbar)
        inflate()
        val configChange = savedInstanceState?.getBoolean(CONFIG_CHANGE) ?: false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.putBoolean(CONFIG_CHANGE,true)
        super.onSaveInstanceState(outState)
    }

    private fun inflate() {
        player_1.src = R.drawable.neon_blue_x
        player_1.srcSetAnimation = R.drawable.x_animation
        player_1.srcBoomAnimation = R.drawable.neon_blue_x_boom
        player_2.src = R.drawable.neon_red_o
        player_2.srcSetAnimation = R.drawable.heart_animation
        player_2.srcBoomAnimation = R.drawable.neon_red_o_boom
        player_1_points.text = player_1.points.toString()
        player_2_points.text = player_2.points.toString()
        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool!!.load(baseContext, Sounds.BOMB_1_SOUND.sound, 0)
        soundPool!!.load(baseContext, Sounds.CPU_POINT_SOUND.sound, 0)
        soundPool!!.load(baseContext, Sounds.PIECES_FLIP_OFF_BOARD.sound, 0)
        soundPool!!.load(baseContext, Sounds.PLAYER_POINT_SOUND.sound, 0)
        soundPool!!.load(baseContext, Sounds.SET_SOUND.sound, 0)
        soundPool!!.load(baseContext, Sounds.TIC_TAC_BOOM_INTRO.sound, 0)
        soundPool!!.load(baseContext, Sounds.WINNER_CELEBRATE_SOUND.sound, 0)
        soundPool!!.load(baseContext, Sounds.WINNING_LINE_SOUND.sound, 0)
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

        imageLine.visibility = View.INVISIBLE
        animationDrawable_3.stop()

        PVP.setBackgroundColor(android.R.drawable.btn_default)
        PVC.setBackgroundColor(Color.CYAN)

        isGameOver = false
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

    fun playGame(cellId:Int,buSelected:ImageButton) {
        if(!isGameOver){
        println("in playGame(cellId:Int,buSelected:ImageButton) function")
        if (ActivePlayer == 1) {
            println("player_1 play")
            player_1.moves.add(cellId)
            setPlayer_1_Animation(buSelected, R.drawable.x_animation)
            vanishAnimations()

            breakFunction(cellId, buSelected, player_1.src)

            ActivePlayer = 2
            if (setPlayer == 1) {
            } else {
                try {
                    val thread = Thread {
                        Thread.sleep(1000)
                        runOnUiThread(Runnable {
                            AutoPlay()
                        })
                    }
                    thread.start()

                } catch (ex: Exception) {
                    Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show()
                }//end of catch block
            }//end of else block
        }//end of if block "ActivePlayer == 1"
        else {
            println("player_2 play")
            player_2.moves.add(cellId)
            ActivePlayer = 1
            setPlayer_2_Animation(buSelected, R.drawable.heart_animation)
            vanishAnimations()

            breakFunction(cellId, buSelected, player_2.src)

        }//end of else block
        }//end of ....
    }//end of playGame function

    fun checkWinner()
    {
        println("in CheckWinner() function \n")
        var winner = -1
        var line = 0
        //row1
        if (player_1.moves.contains(1) && player_1.moves.contains(2) && player_1.moves.contains(3))
        {
            winner = 1
            println("player_1.moves.contains(1) && player_1.moves.contains(2) && player_1.moves.contains(3) \n")
            line = R.drawable.horizontal_top_animation
        }
        if (player_2.moves.contains(1) && player_2.moves.contains(2) && player_2.moves.contains(3))
        {
            winner = 2
            println("player_2.moves.contains(1) && player_2.moves.contains(2) && player_2.moves.contains(3) \n")
            line = R.drawable.horizontal_top_animation
        }
        //row2
        if (player_1.moves.contains(4) && player_1.moves.contains(5) && player_1.moves.contains(6))
        {
            winner = 1
            println("player_1.moves.contains(4) && player_1.moves.contains(5) && player_1.moves.contains(6) \n")
            line = R.drawable.horizontal_line_animation
        }
        if (player_2.moves.contains(4) && player_2.moves.contains(5) && player_2.moves.contains(6))
        {
            winner = 2
            println("player_2.moves.contains(4) && player_2.moves.contains(5) && player_2.moves.contains(6) \n")
            line = R.drawable.horizontal_line_animation
        }
        //row3
        if (player_1.moves.contains(7) && player_1.moves.contains(8) && player_1.moves.contains(9))
        {
            winner = 1
            println("player_1.moves.contains(7) && player_1.moves.contains(8) && player_1.moves.contains(9) \n")
            line = R.drawable.horizontal_bottom_animation
        }
        if (player_2.moves.contains(7) && player_2.moves.contains(8) && player_2.moves.contains(9))
        {
            winner = 2
            println("player_2.moves.contains(7) && player_2.moves.contains(8) && player_2.moves.contains(9) \n")
            line = R.drawable.horizontal_bottom_animation
        }
        //col1
        if (player_1.moves.contains(1) && player_1.moves.contains(4) && player_1.moves.contains(7))
        {
            winner = 1
            println("player_1.moves.contains(1) && player_1.moves.contains(4) && player_1.moves.contains(7) \n")
            line = R.drawable.vertical_left_animation
        }
        if (player_2.moves.contains(1) && player_2.moves.contains(4) && player_2.moves.contains(7))
        {
            winner = 2
            println("player_2.moves.contains(1) && player_2.moves.contains(4) && player_2.moves.contains(7) \n")
            line = R.drawable.vertical_left_animation
        }
        //col2
        if (player_1.moves.contains(2) && player_1.moves.contains(5) && player_1.moves.contains(8))
        {
            winner = 1
            println("player_1.moves.contains(2) && player_1.moves.contains(5) && player_1.moves.contains(8) \n")
            line = R.drawable.vertical_line_animation
        }
        if (player_2.moves.contains(2) && player_2.moves.contains(5) && player_2.moves.contains(8))
        {
            winner = 2
            println("player_2.moves.contains(2) && player_2.moves.contains(5) && player_2.moves.contains(8) \n")
            line = R.drawable.vertical_line_animation
        }
        //col3
        if (player_1.moves.contains(3) && player_1.moves.contains(6) && player_1.moves.contains(9))
        {
            winner = 1
            println("player_1.moves.contains(3) && player_1.moves.contains(6) && player_1.moves.contains(9) \n")
            line = R.drawable.vertical_right_animation
        }
        if (player_2.moves.contains(3) && player_2.moves.contains(6) && player_2.moves.contains(9))
        {
            winner = 2
            println("player_2.moves.contains(3) && player_2.moves.contains(6) && player_2.moves.contains(9) \n")
            line = R.drawable.vertical_right_animation
        }
        //cross1
        if (player_1.moves.contains(1) && player_1.moves.contains(5) && player_1.moves.contains(9))
        {
            winner = 1
            println("player_1.moves.contains(1) && player_1.moves.contains(5) && player_1.moves.contains(9) \n")
            line = R.drawable.negative_slope_line
        }
        if (player_2.moves.contains(1) && player_2.moves.contains(5) && player_2.moves.contains(9))
        {
            winner = 2
            println("player_2.moves.contains(1) && player_2.moves.contains(5) && player_2.moves.contains(9) \n")
            line = R.drawable.negative_slope_line
        }
        //cross2
        if (player_1.moves.contains(3) && player_1.moves.contains(5) && player_1.moves.contains(7))
        {
            winner = 1
            println("player_1.moves.contains(3) && player_1.moves.contains(5) && player_1.moves.contains(7) \n")
            line = R.drawable.positive_slope_line
        }

        if (player_2.moves.contains(3) && player_2.moves.contains(5) && player_2.moves.contains(7))
        {
            winner = 2
            println("player_2.moves.contains(3) && player_2.moves.contains(5) && player_2.moves.contains(7) \n")
            line = R.drawable.positive_slope_line
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
                    winningLineAnimate(line)
                    setPlayerPoints(1)
                    isGameOver = true
                }
                else
                {
                    println("setPlayer not 1 \n")
                    Toast.makeText(this, "You Won!!", Toast.LENGTH_SHORT).show()
                    println("You Won!! \n")
                    stopTouch()
                    winningLineAnimate(line)
                    setPlayerPoints(1)
                    isGameOver = true
                }
            }
            else
            {
                println("winner" +
                        " is -1) \n")
                if (setPlayer == 1) {
                    println("if(setPlayer == 1) \n")
                    Toast.makeText(this, "Player 2 Wins!!", Toast.LENGTH_SHORT).show()
                    println("player 2 wins \n")
                    stopTouch()
                    winningLineAnimate(line)
                    setPlayerPoints(2)
                    isGameOver = true
                }
                else
                {
                    println("else set player was not 1 \n")
                    Toast.makeText(this, "CPU Wins!!", Toast.LENGTH_SHORT).show()
                    println("CPU Wins \n")
                    stopTouch()
                    winningLineAnimate(line)
                    setPlayerPoints(2)
                    isGameOver = true
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
            boomAnimate(getBoomImage(player_1.srcBoomAnimation))
            removeAtIndex = player_1.moves.indexOf(bomb)
            player_1.moves.removeAt(removeAtIndex)
            println("In placeBomb function after remove player_1 moves ${player_1.moves.toString()} \n")
            sweepBoard(bomb)
        }else if(player_2.moves.contains(bomb)){
            println("In placeBomb function player_2.moves contains bomb \n")
            println("In placeBomb function before remove player_2 moves ${player_2.moves.toString()} \n")
            boomAnimate(getBoomImage(player_2.srcBoomAnimation))
            removeAtIndex = player_2.moves.indexOf(bomb)
            player_2.moves.removeAt(removeAtIndex)
            println("In placeBomb function after remove player_2 moves ${player_2.moves.toString()} \n")
            sweepBoard(bomb)
        }//end of else if statement
    }

    fun breakFunction(cellId: Int, buSelected: ImageButton, player_src: Int){
        println("in breakFunction before checking for bombs")
        buSelected.isEnabled = false
        println("in playGame function before checking for bombs")
        println("button ${buSelected.id} enabled = ${buSelected.isEnabled} \n")
        if(cellId == bomb){removePiece(cellId, buSelected)
        }else{
            buSelected.setImageResource(player_src)
            checkWinner()}
        println("in playGame function after checking for bombs")
        println("button ${buSelected.id} enabled = ${buSelected.isEnabled} \n")
        boardBuilder()
    }

    fun removePiece(cellId:Int, buSelected:ImageButton){
        println("In removePiece function \n")
        val removeAtIndex: Int
        print("\n")
        if(player_1.moves.contains(bomb)){
            boomAnimate(getBoomImage(player_1.srcBoomAnimation))
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
            boomAnimate(getBoomImage(player_2.srcBoomAnimation))
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

    fun vanishAnimations(){

        animationDrawable_1.onAnimationFinished {
            imageView_1.visibility = View.GONE
            imageView_2.visibility = View.GONE
            imageView_3.visibility = View.GONE
            imageView_4.visibility = View.GONE
            imageView_5.visibility = View.GONE
            imageView_6.visibility = View.GONE
            imageView_7.visibility = View.GONE
            imageView_8.visibility = View.GONE
            imageView_9.visibility = View.GONE
            animationDrawable_1.stop()
        }

        animationDrawable_2.onAnimationFinished {
            imageView_1.visibility = View.GONE
            imageView_2.visibility = View.GONE
            imageView_3.visibility = View.GONE
            imageView_4.visibility = View.GONE
            imageView_5.visibility = View.GONE
            imageView_6.visibility = View.GONE
            imageView_7.visibility = View.GONE
            imageView_8.visibility = View.GONE
            imageView_9.visibility = View.GONE
            animationDrawable_2.stop()
        }

    }

    fun setPlayer_1_Animation(view: ImageButton, animationId: Int){
        val buSelected: ImageButton = view as ImageButton

        when(buSelected.id)
        {
            R.id.button1 ->{
                imageView_1.setBackgroundResource(animationId)
                animationDrawable_1 = imageView_1.background as AnimationDrawable
                imageView_1.visibility = View.VISIBLE
                animationDrawable_1.start()
                //animationDrawable_1.setVisible(false, false)
                //animationDrawable_1.stop()
            }//end of R.id.button1
            R.id.button2 -> {
                imageView_2.setBackgroundResource(animationId)
                animationDrawable_1 = imageView_2.background as AnimationDrawable
                imageView_2.visibility = View.VISIBLE
                animationDrawable_1.start()
            }//end of R.id.button2
            R.id.button3 ->{
                imageView_3.setBackgroundResource(animationId)
                animationDrawable_1 = imageView_3.background as AnimationDrawable
                imageView_3.visibility = View.VISIBLE
                animationDrawable_1.start()
            }//end of R.id.button3
            R.id.button4 ->{
                imageView_4.setBackgroundResource(animationId)
                animationDrawable_1 = imageView_4.background as AnimationDrawable
                imageView_4.visibility = View.VISIBLE
                animationDrawable_1.start()
            }//end of R.id.button4
            R.id.button5 ->{
                imageView_5.setBackgroundResource(animationId)
                animationDrawable_1 = imageView_5.background as AnimationDrawable
                imageView_5.visibility = View.VISIBLE
                animationDrawable_1.start()
            }//end of R.id.button5
            R.id.button6 ->{
                imageView_6.setBackgroundResource(animationId)
                animationDrawable_1 = imageView_6.background as AnimationDrawable
                imageView_6.visibility = View.VISIBLE
                animationDrawable_1.start()
            }//end of R.id.button6
            R.id.button7 ->{
                imageView_7.setBackgroundResource(animationId)
                animationDrawable_1 = imageView_7.background as AnimationDrawable
                imageView_7.visibility = View.VISIBLE
                animationDrawable_1.start()
            }//end of R.id.button7
            R.id.button8 ->{
                imageView_8.setBackgroundResource(animationId)
                animationDrawable_1 = imageView_8.background as AnimationDrawable
                imageView_8.visibility = View.VISIBLE
                animationDrawable_1.start()
            }//end of R.id.button8
            else ->{
                imageView_9.setBackgroundResource(animationId)
                animationDrawable_1 = imageView_9.background as AnimationDrawable
                imageView_9.visibility = View.VISIBLE
                animationDrawable_1.start()
            }//end of else block
        }//end of when block
        playSound(Sounds.SET_SOUND.soundId)
        playSound(Sounds.SET_SOUND.soundId)
    }//end of setPlayer_1_Animation function

    fun setPlayer_2_Animation(view: ImageButton, animationId: Int){
        val buSelected: ImageButton = view as ImageButton

        when(buSelected.id)
        {
            R.id.button1 ->{
                imageView_1.setBackgroundResource(animationId)
                animationDrawable_2 = imageView_1.background as AnimationDrawable
                imageView_1.visibility = View.VISIBLE
                animationDrawable_2.start()
                //animationDrawable_1.setVisible(false, false)
                //animationDrawable_1.stop()
            }//end of R.id.button1
            R.id.button2 -> {
                imageView_2.setBackgroundResource(animationId)
                animationDrawable_2 = imageView_2.background as AnimationDrawable
                imageView_2.visibility = View.VISIBLE
                animationDrawable_2.start()
            }//end of R.id.button2
            R.id.button3 ->{
                imageView_3.setBackgroundResource(animationId)
                animationDrawable_2 = imageView_3.background as AnimationDrawable
                imageView_3.visibility = View.VISIBLE
                animationDrawable_2.start()
            }//end of R.id.button3
            R.id.button4 ->{
                imageView_4.setBackgroundResource(animationId)
                animationDrawable_2 = imageView_4.background as AnimationDrawable
                imageView_4.visibility = View.VISIBLE
                animationDrawable_2.start()
            }//end of R.id.button4
            R.id.button5 ->{
                imageView_5.setBackgroundResource(animationId)
                animationDrawable_2 = imageView_5.background as AnimationDrawable
                imageView_5.visibility = View.VISIBLE
                animationDrawable_2.start()
            }//end of R.id.button5
            R.id.button6 ->{
                imageView_6.setBackgroundResource(animationId)
                animationDrawable_2 = imageView_6.background as AnimationDrawable
                imageView_6.visibility = View.VISIBLE
                animationDrawable_2.start()
            }//end of R.id.button6
            R.id.button7 ->{
                imageView_7.setBackgroundResource(animationId)
                animationDrawable_2 = imageView_7.background as AnimationDrawable
                imageView_7.visibility = View.VISIBLE
                animationDrawable_2.start()
            }//end of R.id.button7
            R.id.button8 ->{
                imageView_8.setBackgroundResource(animationId)
                animationDrawable_2 = imageView_8.background as AnimationDrawable
                imageView_8.visibility = View.VISIBLE
                animationDrawable_2.start()
            }//end of R.id.button8
            else ->{
                imageView_9.setBackgroundResource(animationId)
                animationDrawable_2 = imageView_9.background as AnimationDrawable
                imageView_9.visibility = View.VISIBLE
                animationDrawable_2.start()
            }//end of else block
        }//end of when block
        playSound(Sounds.SET_SOUND.soundId)
    }//end of setPlayer_2_Animation function

    fun AnimationDrawable.onAnimationFinished(block: () -> Unit) {
        var duration: Long = 0
        for (i in 0..numberOfFrames) {
            duration += getDuration(i)
        }
        Handler().postDelayed({
            block()
        }, duration + 200)
    }

    fun getBoomImage(srcImage: Int): ImageView{
        val boomImage: ImageView

        when(bomb)
        {
            1 -> boomImage = imageViewBoom_1
            2 -> boomImage = imageViewBoom_2
            3 -> boomImage = imageViewBoom_3
            4 -> boomImage = imageViewBoom_4
            5 -> boomImage = imageViewBoom_5
            6 -> boomImage = imageViewBoom_6
            7 -> boomImage = imageViewBoom_7
            8 -> boomImage = imageViewBoom_8
            else -> boomImage = imageViewBoom_9
        }//end of when(bomb) block

        boomImage.setBackgroundResource(srcImage)

        return boomImage
    }//end of getBoomImage function

    fun boomAnimate(boomImage: ImageView){
        boomImage.visibility = View.VISIBLE

        val translation_x = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 200f)

        val translation_y = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 200f)

        val scale_x = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)

        val scale_y = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)

        val rotate = PropertyValuesHolder.ofFloat(View.ROTATION, -1836f, 0f)

        //--------------------------------------------------------------------------------------------------------
        val negative_translation_x = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0f)

        val negative_translation_y = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f)

        val negative_scale_x = PropertyValuesHolder.ofFloat(View.SCALE_X, 0f)

        val negative_scale_y = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f)


        //--------------------------------------------------------------------------------------------------------

        val objectAnimation = ObjectAnimator.ofPropertyValuesHolder(boomImage,translation_x,translation_y,scale_x,scale_y,rotate)
        val negative_objectAnimation = ObjectAnimator.ofPropertyValuesHolder(boomImage,negative_translation_x,negative_translation_y,negative_scale_x,negative_scale_y)

        negative_objectAnimation.repeatCount = 0

        objectAnimation.repeatCount = 1

        println("is animation object running  before start ${objectAnimation.isRunning}")
        objectAnimation.repeatMode = ObjectAnimator.RESTART

        objectAnimation.start()
        playSound(Sounds.PIECES_FLIP_OFF_BOARD.soundId)

        objectAnimation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                boomImage.visibility = View.INVISIBLE
                negative_objectAnimation.start()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })//end of objectAnimation.addListener block

        negative_objectAnimation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                // 3
                boomImage.visibility = View.INVISIBLE
            }

            override fun onAnimationEnd(animation: Animator) {}

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })//end of negative_objectAnimation.addListener block
        playSound(Sounds.BOMB_1_SOUND.soundId)
    }//end of boomAnimate function

    fun winningLineAnimate(lineId: Int){
        println("in winningLineAnimate")
        imageLine.visibility = View.VISIBLE
        imageLine.setBackgroundResource(lineId)
        animationDrawable_3 = imageLine.background as AnimationDrawable
        animationDrawable_3.start()
        playSound(Sounds.WINNING_LINE_SOUND.soundId)
        println("is animationDrawable_3 running = ${animationDrawable_3.isRunning}")
    }

    fun playSound(soundId: Int) {
        println("in playSound ")
        soundPool?.play(soundId, 1F, 1F, 0, 0, 1F)
    }

    fun setPlayerPoints(playerId: Int){
        when(playerId){
            1 -> player_1.points += player_1.moves.size * 100
            2 -> player_2.points += player_2.moves.size * 100
        }
        player_1_points.text = player_1.points.toString()
        player_2_points.text = player_2.points.toString()
        playSound(Sounds.PLAYER_POINT_SOUND.soundId)
    }

}