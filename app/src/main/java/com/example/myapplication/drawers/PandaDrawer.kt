package com.example.myapplication.drawers

import android.app.Activity
import android.view.View
import android.widget.FrameLayout
import com.example.myapplication.*
import com.example.myapplication.bd.Elements
import com.example.myapplication.enums.Direction
import com.example.myapplication.enums.Direction.*
import com.example.myapplication.enums.Material
import com.example.myapplication.models.Coordinate

class PandaDrawer(private val container: FrameLayout){

    private val activity = container.context as Activity

    private fun  getElementByCoordinate(coordinate: Coordinate, elementsOnContainer: List<Elements>) =
        elementsOnContainer.firstOrNull {it.coordinateX == coordinate.top && it.coordinateY == coordinate.left }

    fun move(myPanda: View, direction: Direction, elementsOnContainer: List<Elements>, elementsContainer: MutableList<Elements>) {
        val layoutParams = myPanda.layoutParams as FrameLayout.LayoutParams
        val currentCoordinate = Coordinate(layoutParams.topMargin, layoutParams.leftMargin)
        myPanda.setBackgroundResource(R.drawable.panda_top)
 //       Thread {
            when (direction) {
                UP -> {
                    myPanda.rotation = 0f
                    (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin -= CELL_SIZE * sizeElements
                }
                DOWN -> {
                    myPanda.rotation = 180f
                    (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE * sizeElements
                }
                LEFT -> {
                    myPanda.rotation = 270f
                    (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin -= CELL_SIZE * sizeElements
                }
                RIGHT -> {
                    myPanda.rotation = 90f
                    (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE * sizeElements
                }
                EAT -> {
                    myPanda.rotation = 0f
                    myPanda.setBackgroundResource(R.drawable.panda_eat)
                    compareCollection(
                        elementsContainer, getPandaCoordinates(
                            Coordinate(
                                (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin,
                                (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin
                            )
                        )
                    )
                }
            }
            val nextCoordinate = Coordinate(layoutParams.topMargin, layoutParams.leftMargin)
            if (checkPandaCanMoveThroughBorder(nextCoordinate, myPanda) && checkPandaCanMoveThroughMaterial(nextCoordinate, elementsOnContainer)
            ) {
                container.removeView(myPanda)
                container.addView(myPanda)
            } else {
                (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin = currentCoordinate.top
                (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin =
                    currentCoordinate.left
            }
 //           Thread.sleep(300)
 //       }.start()
    }
////////// удаление бамбука
    private fun compareCollection(elementsOnContainer: MutableList<Elements>, coordinateList: List<Coordinate>){
        coordinateList.forEach {
            val element = getElementByCoordinate(it, elementsOnContainer)
            removeBambooContainer(element, elementsOnContainer)
        }
    }

    private fun removeBambooContainer(element: Elements?, elementsOnContainer: MutableList<Elements>) {
        if (element != null){
            if (element.material == Material.BAMBOO){
                removeElement(element)
                elementsOnContainer.remove(element)
            }
        }
    }

    private fun removeElement(element: Elements) {
        activity.runOnUiThread {
            container.removeView(activity.findViewById(element.viewId))
        }
    }
////////////

    private fun checkPandaCanMoveThroughMaterial(coordinate: Coordinate, elementsOnContainer: List<Elements>):Boolean{
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

