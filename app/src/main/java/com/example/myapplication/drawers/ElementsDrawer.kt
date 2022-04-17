package com.example.myapplication.drawers

import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.myapplication.*
import com.example.myapplication.enums.Direction
import com.example.myapplication.enums.Material
import com.example.myapplication.models.Coordinate
import com.example.myapplication.models.Element
import kotlinx.android.synthetic.main.game_layout.*

class ElementsDrawer(val container: FrameLayout) {
    var currentMaterial = Material.EMPTY
    val elementsOnContainer = mutableListOf<Element>()

    fun onTouchContainer(x:Float, y:Float){
        val topMargin = y.toInt() - (y.toInt()% 120)
        val leftMargin = x.toInt() - (x.toInt()% 120)
        val coordinate = Coordinate(topMargin, leftMargin)
        if(currentMaterial == Material.EMPTY){
            eraseView(coordinate)
        }else{
            drawOrReplaceView(coordinate)
        }
    }

    private fun drawOrReplaceView(coordinate: Coordinate){
        val viewOnCoordinate = getElementByCoordinate(coordinate)
        if (viewOnCoordinate == null){
            drawView(coordinate)
            return
        }
        if (viewOnCoordinate.material != currentMaterial){
            replaceView(coordinate)
        }
    }

    private fun  getElementByCoordinate(coordinate: Coordinate) = elementsOnContainer.firstOrNull { it.coordinate == coordinate }

    private fun replaceView(coordinate: Coordinate){
        eraseView(coordinate)
        drawView(coordinate)
    }

      private fun eraseView(coordinate: Coordinate){
        val elementOnCoordinate = getElementByCoordinate(coordinate)
        if (elementOnCoordinate != null){
            val erasingView = container.findViewById<View>(elementOnCoordinate.viewId)
            container.removeView(erasingView)
            elementsOnContainer.remove(elementOnCoordinate)
        }
    }

    private fun removeUnwantedInstances() {
        if (currentMaterial.elementsAmountOnScreen != 0) {
            val erasingElements = elementsOnContainer.filter { it.material == currentMaterial }
            if (erasingElements.size >=  currentMaterial.elementsAmountOnScreen) {
                eraseView(erasingElements[0].coordinate)
            }
        }
    }

    private fun drawView(coordinate: Coordinate){
        removeUnwantedInstances()
        val view = ImageView(container.context)
        val layoutParams = FrameLayout.LayoutParams(120, 120)
        when(currentMaterial){
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
//        Log.d("elem", elementsOnContainer.toString())
    }

     fun deleteAllElement(){


    }

    fun drawElementsList(elements: List<Element>?){
        if (elements == null){
            return
        }
        for (element in elements){
            currentMaterial = element.material
            drawView(element.coordinate)
        }
    }
}