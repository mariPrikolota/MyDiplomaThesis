package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.home_layout.*


class NavigationActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)

        configureClickPlay()
    }

    private fun configureClickPlay(){
        playGame.setOnClickListener {
            val intent = Intent(this, LevelGame::class.java)
            startActivity(intent)
        }
    }
}