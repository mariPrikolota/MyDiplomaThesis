package com.example.myapplication.drawers

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.GamePlayer
import com.example.myapplication.R
import com.example.myapplication.adapter.StepAdapter
import com.example.myapplication.level.StepItem
import com.example.myapplication.models.Step

class StepDrawer(val container: RecyclerView, context: Context) {
//    val stepOnPanda = mutableListOf<Step>()
    val context = context

     fun stepBind(){
        container.layoutManager = GridLayoutManager(context,5)
        container.adapter = StepAdapter(context)
         val step = StepItem(R.drawable.icon_up)
        StepAdapter(context).addStep(step)
    }
}