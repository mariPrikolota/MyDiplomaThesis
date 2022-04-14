package com.example.myapplication.drawers

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.StepAdapter
import com.example.myapplication.enums.Direction
import com.example.myapplication.enums.Direction.*
import com.example.myapplication.level.StepItem
import com.example.myapplication.models.Element
import com.example.myapplication.models.Step
import kotlinx.android.synthetic.main.game_layout.*

class StepDrawer(val container: RecyclerView, context: Context) {
    val context = context
    private val stepAdapter = StepAdapter()
    val stepOnContainer = mutableListOf<Step>()
    private var stepItem : StepItem? = null

     fun stepView(direction: Direction){
        container.layoutManager = GridLayoutManager(context,5)
        container.adapter = stepAdapter
        val step = ImageView(container.context)
        when(direction){
            UP -> {
                stepItem = StepItem(R.drawable.icon_up)
            }
            DOWN -> {
                stepItem = StepItem(R.drawable.icon_down)
            }
            LEFT -> {
                stepItem = StepItem(R.drawable.icon_left)
            }
            RIGHT -> {
                stepItem = StepItem(R.drawable.icon_right)
            }
            EAT -> {
                stepItem = StepItem(R.drawable.step_eat)
            }
        }
        val stepId = View.generateViewId()
        step.id = stepId
        stepAdapter.addStep(stepItem!!)
        stepOnContainer.add(Step(stepId,direction))
//         Log.d("step", stepOnContainer.toString())
    }

    fun eraseStep(){
        stepAdapter.deleteStep(stepItem!!)
    }
}