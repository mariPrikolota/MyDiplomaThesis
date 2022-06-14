package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.adapter.LevelAdapter
import com.example.myapplication.adapter.OnOpenDialogClickListener
import com.example.myapplication.dialog.OnDeleteLevelClickListener
import com.example.myapplication.bd.Level
import com.example.myapplication.bd.RoomAppDB
import com.example.myapplication.dialog.DeleteDialog
import kotlinx.android.synthetic.main.level_layout.*

class LevelGame:  AppCompatActivity(), OnOpenDialogClickListener, OnDeleteLevelClickListener {
    private var level: List<Level>? = null
    var numberLevel: Int? = null



    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level_layout)
        onClick()
        getAllLevel()

        recyclerViewLevel.hasFixedSize()
        recyclerViewLevel.layoutManager = GridLayoutManager(this, 5)
        recyclerViewLevel.adapter = LevelAdapter(level!!, this, this)
        recyclerViewLevel?.adapter?.notifyDataSetChanged()
    }


     private fun getAllLevel() {
         val list =  RoomAppDB.getAppDB(this)?.levelDao()
         level = list?.getAllLevel()
    }

    override fun onOpenDialog(int: Int) {
        DeleteDialog(this).show(supportFragmentManager, "DeleteDialog")
        numberLevel = int
    }

    private fun onClick(){
        backButton.setOnClickListener {  finish() }
        createLevel.setOnClickListener {
            finish()
            val intent = Intent(this, GamePlayer::class.java)
            intent.putExtra("editMode", true)
            startActivity(intent)
        }
    }
    override fun onDeleteLevel(boolean: Boolean) {
        val list1 =  RoomAppDB.getAppDB(this)?.levelDao()
        if (boolean){
            if(numberLevel != null) {
                list1?.deleteLevel(numberLevel!!)
            }
        }
    }
}