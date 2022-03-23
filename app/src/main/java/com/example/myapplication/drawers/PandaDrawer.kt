package com.example.myapplication.drawers

import android.view.View
import android.widget.FrameLayout
import com.example.myapplication.CELL_SIZE
import com.example.myapplication.MAX_HORIZONTAL
import com.example.myapplication.MAX_VERTICAL
import com.example.myapplication.R
import com.example.myapplication.enums.Direction
import com.example.myapplication.models.Coordinate
import com.example.myapplication.models.Element

class PandaDrawer(val container: FrameLayout) {

    private fun  getElementByCoordinate(coordinate: Coordinate, elementsOnContainer: List<Element>) =
        elementsOnContainer.firstOrNull { it.coordinate == coordinate }

    fun move(myPanda: View, direction: Direction, elementsOnContainer: List<Element>){
        val layoutParams = myPanda.layoutParams as FrameLayout.LayoutParams
        myPanda.setBackgroundResource(R.drawable.panda_top)
        val currentCoordinate = Coordinate(layoutParams.topMargin, layoutParams.leftMargin)
        when(direction){
            Direction.UP -> {
                myPanda.rotation = 0f
                (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin += -120
            }
            Direction.DOWN -> {
                myPanda.rotation = 180f
                (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin += 120
            }
            Direction.LEFT -> {
                myPanda.rotation = 270f
                (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin -= 120
            }
            Direction.RIGHT -> {
                myPanda.rotation = 90f
                (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin += 120
            }
            Direction.EAT -> {
                myPanda.rotation = 0f
                myPanda.setBackgroundResource(R.drawable.panda_eat)

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