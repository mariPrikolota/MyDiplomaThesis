package com.example.myapplication.Level

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.example.myapplication.R

class GridDrawer(private val context: Context) {

    private val allLines = mutableListOf<View>()

    fun removeGrid(){
        val container = (context as Activity).findViewById<FrameLayout>(R.id.container)
        allLines.forEach {
            container.removeView(it)
        }

    }

    fun drawGrid(){
        val container = (context as Activity).findViewById<FrameLayout>(R.id.container)
        drawHorizontalLines(container)
        drawVerticalLines(container)
    }

    private fun drawVerticalLines(container: FrameLayout?) {
        var leftMargin = 60
        if (container != null) {
            while (leftMargin <= container.layoutParams.width) {
                val verticalLine = View(container.context)
                val layoutParams = FrameLayout.LayoutParams(1, FrameLayout.LayoutParams.MATCH_PARENT)
                leftMargin += 60
                layoutParams.leftMargin = leftMargin
                verticalLine.layoutParams = layoutParams
                verticalLine.setBackgroundColor(container.context.resources.getColor(R.color.black))
                allLines.add(verticalLine)
                container.addView(verticalLine)
            }
        }

    }

    private fun drawHorizontalLines(container: FrameLayout?) {
        var topMargin = 60
        if (container != null) {
            while (topMargin <= container.layoutParams.height) {
                val horizontalLine = View(container.context)
                val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 1)
                topMargin += 60
                horizontalLine.layoutParams = layoutParams
                horizontalLine.setBackgroundColor(container.context.resources.getColor(R.color.black))
                layoutParams.topMargin = topMargin
                allLines.add(horizontalLine)
                container.addView(horizontalLine)
            }
        }
    }
}