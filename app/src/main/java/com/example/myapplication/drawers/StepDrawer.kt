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
import com.example.myapplication.models.StepItem
import com.example.myapplication.models.Step

var numberStepFunction = true
var stepOnContainer = mutableListOf<Step>()
var stepFunctionContainer = mutableListOf<Step>()

class StepDrawer(val container: RecyclerView, val context: Context){
    private val stepAdapter = StepAdapter()
    private var stepItem : StepItem? = null

     fun stepView(direction: Direction){
        container.layoutManager = GridLayoutManager(context,5)
        container.adapter = stepAdapter
        val step = ImageView(container.context)
        when(direction){
            UP -> {
                stepItem = StepItem(R.drawable.icon_up)
            }
            JUMP -> {
                stepItem = StepItem(R.drawable.jump)
            }
            LEFT -> {
                stepItem = StepItem(R.drawable.left)
            }
            RIGHT -> {
                stepItem = StepItem(R.drawable.right)
            }
            CORNER_LEFT -> {
                stepItem = StepItem(R.drawable.corner_left_jump)
            }
            CORNER_RIGHT -> {
                stepItem = StepItem(R.drawable.corner_right_jump)
            }
            EAT -> {
                stepItem = StepItem(R.drawable.step_eat)
            }
            FUN -> stepItem = StepItem(R.drawable.step_function)
        }
         val stepId = View.generateViewId()
         step.id = stepId
         stepAdapter.addStep(stepItem!!)
         if (direction != FUN){
             if (numberStepFunction ){
                 stepOnContainer.add(Step(stepId, direction))
             }else{
                 stepFunctionContainer.add(Step(stepId, direction))
             }
         }

         Log.d("step", stepOnContainer.toString())
         Log.d("step2", stepFunctionContainer.toString())
     }

    // стирание последнего элемента
    fun eraseStep() {
        stepAdapter.deleteAllStep()
        stepOnContainer.clear()
    }

//    удвление всех элементов
    fun eraseListAndContainer(){
        stepAdapter.deleteAllStep()
        stepOnContainer.clear()
    }
}