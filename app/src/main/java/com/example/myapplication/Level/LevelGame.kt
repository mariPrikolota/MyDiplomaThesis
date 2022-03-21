package com.example.myapplication.Level

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.example.myapplication.R

class LevelGame:  Activity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level_layout)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.backButton -> {}
            R.id.createLevel -> {
            }

        }
    }
}