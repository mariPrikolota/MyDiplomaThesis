package com.example.myapplication.level

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.GamePlayer
import com.example.myapplication.R
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