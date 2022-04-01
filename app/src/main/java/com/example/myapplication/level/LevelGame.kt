package com.example.myapplication.level

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.GamePlayer
import com.example.myapplication.R
import com.example.myapplication.drawers.GridDrawer
import kotlinx.android.synthetic.main.game_layout.*
import kotlinx.android.synthetic.main.level_layout.*

class LevelGame:  AppCompatActivity(), View.OnClickListener{

    private val gridDrawer by lazy {
        GridDrawer(container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level_layout)
        onKeyButton()

        var list = ArrayList<LevelItem>()
        list.add(LevelItem(1))
        list.add(LevelItem(2))
        list.add(LevelItem(3))
        list.add(LevelItem(4))
        list.add(LevelItem(5))
        list.add(LevelItem(6))
        list.add(LevelItem(7))
        list.add(LevelItem(8))

        recyclerViewLevel.hasFixedSize()
        recyclerViewLevel.layoutManager = GridLayoutManager(this, 5)
        recyclerViewLevel.adapter = LevelAdapter(list, this)
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