package com.example.myapplication.Level

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.myapplication.GamePlayer
import com.example.myapplication.R
import kotlinx.android.synthetic.main.game_layout.*
import kotlinx.android.synthetic.main.level_layout.*

class LevelGame:  Activity(), View.OnClickListener{

//    private var editMode = false
//
//    private val gridDrawer by lazy {
//        GridDrawer(this)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level_layout)
        onKeyButton()
    }

    private fun onKeyButton(){
        backButton.setOnClickListener(this)
        createLevel.setOnClickListener(this)
        buttonPrimer.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.backButton -> {
                finish()
            }
            R.id.createLevel -> {
                val intent = Intent(this, GamePlayer::class.java)
                startActivity(intent)
            }
            R.id.buttonPrimer -> {
                val intent = Intent(this, GamePlayer::class.java)
                startActivity(intent)
            }
        }
    }

//    private fun switchEditMode(){
//        if (editMode){
//            gridDrawer.removeGrid()
//            materialContainer.visibility = VISIBLE
//            stepContainer.visibility = GONE
//        }else{
//            gridDrawer.drawGrid()
//
//            materialContainer.visibility = GONE
//            stepContainer.visibility = VISIBLE
//        }
//        editMode = !editMode
//    }

}