package com.example.myapplication.drawers

import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.game_layout.view.*

class LevelDrawer(private val container: FrameLayout) {
    fun stepStudyLevel(level: String?) {
        when(level){
            "1" -> {
                container.leftView.visibility = View.GONE
                container.rightView.visibility = View.GONE
                container.jumpView.visibility = View.GONE
                container.cornerLeftView.visibility = View.GONE
                container.cornerRightView.visibility = View.GONE
                container.functionMaterialView.visibility = View.GONE
                container.deleteFunStep.visibility = View.GONE
                container.functionOne.visibility = View.GONE
            }
            "2" -> {
                container.rightView.visibility = View.GONE
                container.jumpView.visibility = View.GONE
                container.cornerLeftView.visibility = View.GONE
                container.cornerRightView.visibility = View.GONE
                container.functionMaterialView.visibility = View.GONE
                container.deleteFunStep.visibility = View.GONE
                container.functionOne.visibility = View.GONE
            }
            "3" -> {
                container.leftView.visibility = View.GONE
                container.rightView.visibility = View.GONE
                container.cornerLeftView.visibility = View.GONE
                container.cornerRightView.visibility = View.GONE
                container.functionMaterialView.visibility = View.GONE
                container.deleteFunStep.visibility = View.GONE
                container.functionOne.visibility = View.GONE
            }
            "4" -> {
                container.leftView.visibility = View.GONE
                container.rightView.visibility = View.GONE
                container.jumpView.visibility = View.GONE
                container.cornerLeftView.visibility = View.GONE
                container.functionMaterialView.visibility = View.GONE
                container.deleteFunStep.visibility = View.GONE
                container.functionOne.visibility = View.GONE
            }
            "5" -> {
                container.leftView.visibility = View.GONE
                container.rightView.visibility = View.GONE
                container.jumpView.visibility = View.GONE
                container.cornerRightView.visibility = View.GONE
                container.functionMaterialView.visibility = View.GONE
                container.deleteFunStep.visibility = View.GONE
                container.functionOne.visibility = View.GONE
            }
            "6" -> {
                container.leftView.visibility = View.GONE
                container.jumpView.visibility = View.GONE
                container.cornerRightView.visibility = View.GONE
                container.functionMaterialView.visibility = View.GONE
                container.deleteFunStep.visibility = View.GONE
                container.functionOne.visibility = View.GONE
            }
            "7" -> {
                container.rightView.visibility = View.GONE
                container.jumpView.visibility = View.GONE
                container.cornerRightView.visibility = View.GONE
                container.functionMaterialView.visibility = View.GONE
                container.deleteFunStep.visibility = View.GONE
                container.functionOne.visibility = View.GONE
            }
        }
    }
}