package com.example.myapplication.drawers

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.myapplication.CELL_SIZE
import com.example.myapplication.MAX_HORIZONTAL
import com.example.myapplication.MAX_VERTICAL
import com.example.myapplication.R
import com.example.myapplication.enums.Direction
import com.example.myapplication.enums.Direction.*
import com.example.myapplication.enums.Material
import com.example.myapplication.models.Coordinate
import com.example.myapplication.models.Element
import kotlinx.android.synthetic.main.game_layout.*

class PandaDrawer(private val container: FrameLayout){

    private val activity = container.context as Activity

    private val elementDrawer by lazy {
        ElementsDrawer(container)
    }

    private fun  getElementByCoordinate(coordinate: Coordinate, elementsOnContainer: List<Element>) =
        elementsOnContainer.firstOrNull { it.coordinate == coordinate }

    fun move(myPanda: View, direction: Direction, elementsOnContainer: List<Element>, elementsContainer: MutableList<Element>){
        val layoutParams = myPanda.layoutParams as FrameLayout.LayoutParams

        myPanda.setBackgroundResource(R.drawable.panda_top)
        val currentCoordinate = Coordinate(layoutParams.topMargin, layoutParams.leftMargin)
        when(direction){
            UP -> {
                myPanda.rotation = 0f
                (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin += -120
            }
            DOWN -> {
                myPanda.rotation = 180f
                (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin += 120
            }
            LEFT -> {
                myPanda.rotation = 270f
                (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin -= 120
            }
            RIGHT -> {
                myPanda.rotation = 90f
                (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin += 120
            }
            EAT -> {
                myPanda.rotation = 0f
                myPanda.setBackgroundResource(R.drawable.panda_eat)
                checkPandaEat(elementsContainer, Coordinate(
                    (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin,
                    (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin
                ))
            }
        }
        val nextCoordinate =  Coordinate(layoutParams.topMargin, layoutParams.leftMargin)
        if (checkPandaCanMoveThroughBorder(nextCoordinate, myPanda) && checkPandaCanMoveThroughMaterial(nextCoordinate, elementsOnContainer)){
            container.removeView(myPanda)
            container.addView(myPanda)
        } else {
            (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin = currentCoordinate.top
            (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin = currentCoordinate.left
        }
    }
// удаление бамбука
    private fun checkPandaEat(elementsOnContainer: MutableList<Element>, pandaCoordinate: Coordinate){
        compareCollection(elementsOnContainer, getPandaCoordinates(pandaCoordinate))
    }

    private fun compareCollection(elementsOnContainer: MutableList<Element>, coordinateList: List<Coordinate>){
        val viewId = View.generateViewId()
        coordinateList.forEach {
            val element = getElementByCoordinate(it, elementsOnContainer)
            removeBamboo(element, elementsOnContainer)
            elementsOnContainer.add(Element(viewId, Material.EMPTY, it))
        }
    }

    private fun removeBamboo(element: Element?, elementsOnContainer: MutableList<Element>) {
        if (element != null){
            if (element.material == Material.BAMBOO){
                removeElement(element)
                elementsOnContainer.remove(element)
            }
        }
    }

    private fun removeElement(element: Element) {
        activity.runOnUiThread {
            container.removeView(activity.findViewById(element.viewId))
        }
    }
///

    private fun checkPandaCanMoveThroughMaterial(coordinate: Coordinate, elementsOnContainer: List<Element>):Boolean{
        getPandaCoordinates(coordinate).forEach {
            val element = getElementByCoordinate(it, elementsOnContainer)
            if (element != null){
                return true
            }
        }
        return false
    }

    private fun checkPandaCanMoveThroughBorder(coordinate: Coordinate, myPanda: View): Boolean{
        if (coordinate.top >= 0 && coordinate.top + myPanda.height < MAX_HORIZONTAL &&
            coordinate.left >= 0 && coordinate.left + myPanda.width < MAX_VERTICAL
        ){
            return true
        }
        return false
    }

    private fun getPandaCoordinates(topLeftCoordinate: Coordinate): List<Coordinate>{
        val coordinateList = mutableListOf<Coordinate>()
        coordinateList.add(topLeftCoordinate)
        coordinateList.add(Coordinate(topLeftCoordinate.top + CELL_SIZE, topLeftCoordinate.left))
        coordinateList.add(Coordinate(topLeftCoordinate.top , topLeftCoordinate.left + CELL_SIZE))
        coordinateList.add(Coordinate(topLeftCoordinate.top + CELL_SIZE, topLeftCoordinate.left + CELL_SIZE))
        return coordinateList
    }
}

