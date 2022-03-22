package com.example.myapplication.drawers

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.myapplication.CELL_SIZE
import com.example.myapplication.R
import com.example.myapplication.enums.Material
import com.example.myapplication.models.Coordinate
import com.example.myapplication.models.Element

class ElementsDrawer(val container: FrameLayout) {
    var currentMaterial = Material.EMPTY
    private val elementsOnContainer = mutableListOf<Element>()

    fun onTouchContainer(x:Float, y:Float){
        val topMargin = y.toInt() - (y.toInt()% 120)
        val leftMargin = x.toInt() - (x.toInt()% 120)
        val coordinate = Coordinate(topMargin, leftMargin)
        drawView(coordinate)
    }

    private fun drawView(coordinate: Coordinate){
        val view = ImageView(container.context)
        val layoutParams = FrameLayout.LayoutParams(120, 120)
        when(currentMaterial){
            Material.EMPTY -> {}
            Material.STONE -> view.setImageResource(R.drawable.stone)
            Material.TREE -> view.setImageResource(R.drawable.tree)
            Material.BAMBOO -> view.setImageResource(R.drawable.bamboo)
            Material.PANDA -> view.setImageResource(R.drawable.panda_stop)
        }
        layoutParams.topMargin = coordinate.top
        layoutParams.leftMargin= coordinate.left
        val viewId = View.generateViewId()
        view.id = viewId
        view.layoutParams = layoutParams
        container.addView(view)
        elementsOnContainer.add(Element(viewId, currentMaterial, coordinate))
    }
}