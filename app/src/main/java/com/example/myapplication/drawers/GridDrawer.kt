package com.example.myapplication.drawers

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.example.myapplication.CELL_SIZE
import com.example.myapplication.R
import com.example.myapplication.sizeElements
import kotlinx.android.synthetic.main.size_material.*

class GridDrawer(private val container: FrameLayout) {

//    private val allLines = mutableListOf<View>()
//
//    fun removeGrid(){
//        allLines.forEach {
//            container.removeView(it)
//        }
//    }
//
//    @SuppressLint("ResourceAsColor")
//    fun drawGrid(){
//        drawHorizontalLines()
//        drawVerticalLines()
//    }

//    private fun drawVerticalLines() {
//        var leftMargin = 30 * sizeElements
//        while (leftMargin <= container.layoutParams.width) {
//            val verticalLine = View(container.context)
//            val layoutParams = FrameLayout.LayoutParams(1, FrameLayout.LayoutParams.MATCH_PARENT)
//            leftMargin += 30 * sizeElements
//            layoutParams.leftMargin = leftMargin
//            verticalLine.layoutParams = layoutParams
//            verticalLine.setBackgroundColor(container.context.resources.getColor(R.color.black))
//            allLines.add(verticalLine)
//            container.addView(verticalLine)
//
//        }
//    }
//
//    private fun drawHorizontalLines() {
//        var topMargin = 30 * sizeElements
//        while (topMargin <= container.layoutParams.height) {
//            val horizontalLine = View(container.context)
//            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 1)
//            topMargin += 30 * sizeElements
//            horizontalLine.layoutParams = layoutParams
//            horizontalLine.setBackgroundColor(container.context.resources.getColor(R.color.black))
//            layoutParams.topMargin = topMargin
//            allLines.add(horizontalLine)
//            container.addView(horizontalLine)
//        }
//    }
}