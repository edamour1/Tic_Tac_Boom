package com.example.tictacboom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        play_game_option_button.setOnClickListener{
            val  intent = Intent(this, PlayGameActivity::class.java)
            startActivity(intent)
        }
    }
}