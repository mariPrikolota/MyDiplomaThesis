package com.example.myapplication.drawers

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.GamePlayer
import com.example.myapplication.R
import com.example.myapplication.adapter.StepAdapter
import com.example.myapplication.enums.Direction
import com.example.myapplication.enums.Direction.*
import com.example.myapplication.models.StepItem
import com.example.myapplication.models.Step

var numberStepFunction = true

class StepDrawer(val container: RecyclerView, val context: Context){
    private val stepAdapter = StepAdapter()
    var stepOnContainer = mutableListOf<Step>()
    val stepFunctionContainer = mutableListOf<Step>()
    private var stepItem : StepItem? = null
    var position = 0

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
            FUN -> stepItem = StepItem(R.drawable.step_function)
        }
         val stepId = View.generateViewId()
         step.id = stepId
         stepAdapter.addStep(stepItem!!)
         Log.d("num", numberStepFunction.toString())
         if (numberStepFunction ){
             stepOnContainer.add(Step(stepId, direction))
         }else{
             stepFunctionContainer.add(Step(stepId, direction))
         }
         position ++
         Log.d("step", stepOnContainer.toString())
         Log.d("step", stepFunctionContainer.toString())
     }

    // стирание последнего элемента
    fun eraseStep(posit: Int) {
        val stepContainer = stepOnContainer.last()
        stepAdapter.deleteStep(posit)
        stepOnContainer.remove(stepContainer)
        position --
    }

//    удвление всех элементов
    fun eraseListAndContainer(){
        stepAdapter.deleteAllStep()
        stepOnContainer.clear()
    }
}