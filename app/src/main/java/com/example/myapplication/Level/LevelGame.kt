package com.example.myapplication.Level

import android.app.Activity
import android.os.Bundle
import com.example.myapplication.R

class LevelGame:  Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level_layout)
    }
}