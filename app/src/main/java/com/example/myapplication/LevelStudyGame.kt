package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.adapter.LevelStudyAdapter
import com.example.myapplication.bd.LevelStudy
import kotlinx.android.synthetic.main.game_layout.*
import kotlinx.android.synthetic.main.level_study_game.*

class LevelStudyGame: AppCompatActivity() {
    val list = ArrayList<LevelStudy>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level_study_game)
        onClick()

        list.addAll(filArray(resources.getStringArray(R.array.level)))
        recyclerViewStudyLevel.hasFixedSize()
        recyclerViewStudyLevel.layoutManager = GridLayoutManager(this, 5)
        recyclerViewStudyLevel.adapter = LevelStudyAdapter(list, this)
    }

    private fun onClick(){
        back.setOnClickListener { finish() }
    }

    private fun filArray(stringLevel: Array<String>): List<LevelStudy>{
        val listLevelArray = ArrayList<LevelStudy>()
        for (n in stringLevel.indices){
            val listLevel = LevelStudy(n+1, stringLevel[n])
            listLevelArray.add(listLevel)
        }
        return listLevelArray
    }
}