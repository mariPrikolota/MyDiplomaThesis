package com.example.myapplication.level

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.GamePlayer
import com.example.myapplication.R
import com.example.myapplication.adapter.LevelAdapter
import com.example.myapplication.bd.Level
import com.example.myapplication.bd.RoomAppDB
import com.example.myapplication.drawers.GridDrawer
import kotlinx.android.synthetic.main.game_layout.*
import kotlinx.android.synthetic.main.level_layout.*

class LevelGame:  AppCompatActivity(), View.OnClickListener{

    private var level: List<Level>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level_layout)
        onKeyButton()
        getAllLevel()

        recyclerViewLevel.hasFixedSize()
        recyclerViewLevel.layoutManager = GridLayoutManager(this, 5)
        if (level == null) level = emptyList()
        recyclerViewLevel.adapter = LevelAdapter(level!!, this)

    }

    private fun getAllLevel() {
        val list =  RoomAppDB.getAppDB(this)?.levelDao()
        level = list?.getAllLevel()
    }


    private fun onKeyButton(){
        backButton.setOnClickListener(this)
        createLevel.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.backButton -> {
                finish()
            }
            R.id.createLevel -> {
                val intent = Intent(this, GamePlayer::class.java)
                intent.putExtra("editMode", true)
                startActivity(intent)
            }
        }
    }
}