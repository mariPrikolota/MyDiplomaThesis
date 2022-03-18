package com.example.myapplication

import android.app.Activity
import android.os.Bundle


class GamePlayer: Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

    }
}