package com.example.myapplication.drawers

import android.app.Activity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.myapplication.*
import com.example.myapplication.bd.Elements
import com.example.myapplication.enums.Direction
import com.example.myapplication.enums.Direction.*
import com.example.myapplication.enums.Material
import com.example.myapplication.models.Coordinate

interface OnStopGameClickListener{
    fun stopGame(boolean: Boolean)
}

class PandaDrawer(private val container: FrameLayout,val list: OnStopGameClickListener){ //val list: OnStopGameClickListener

    private val activity = container.context as Activity

    private fun  getElementByCoordinate(coordinate: Coordinate, elementsOnContainer: List<Elements>) =
        elementsOnContainer.firstOrNull {it.coordinateX == coordinate.top && it.coordinateY == coordinate.left }

    fun move(myPanda: View, direction: Direction, elementsContainer: MutableList<Elements>, elementsOnContainer: List<Elements>) {
        val layoutParams = myPanda.layoutParams as FrameLayout.LayoutParams
        val currentCoordinate = Coordinate(layoutParams.topMargin, layoutParams.leftMargin)
        myPanda.setBackgroundResource(R.drawable.panda_top)
            when (direction) {
                UP -> {
                    when (myPanda.rotation){
                        0f -> { (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin -= CELL_SIZE * sizeElements }
                        180f -> {   (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE * sizeElements}
                        270f -> { (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin -= CELL_SIZE * sizeElements}
                        90f -> {(myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE * sizeElements}
                    }
                }
                LEFT -> {
                    when (myPanda.rotation){
                        0f -> { myPanda.rotation = 270f }
                        180f ->{ myPanda.rotation = 90f }
                        270f -> { myPanda.rotation = 180f }
                        90f -> { myPanda.rotation = 0f }
                    }

                }
                RIGHT -> {
                    when (myPanda.rotation){
                        0f -> { myPanda.rotation = 90f }
                        180f -> { myPanda.rotation = 270f}
                        270f -> { myPanda.rotation = 0f}
                        90f -> {myPanda.rotation = 180f}
                    }
                }
                JUMP -> {
                    when (myPanda.rotation){
                    0f -> { (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin -= CELL_SIZE * sizeElements * 2}
                    180f -> { (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE * sizeElements * 2}
                    270f -> { (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin -= CELL_SIZE * sizeElements * 2}
                    90f -> { (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE * sizeElements * 2}
                    }
                }
                CORNER_LEFT -> {
                    when (myPanda.rotation){
                        0f -> {
                            (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin -= CELL_SIZE * sizeElements
                            (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin -= CELL_SIZE * sizeElements
                            myPanda.rotation = 270f
                        }
                        180f -> {
                            (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE * sizeElements
                            (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin -= CELL_SIZE * sizeElements
                            myPanda.rotation = 270f
                        }
                        270f -> {
                            (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE * sizeElements
                            (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin -= CELL_SIZE * sizeElements
                            myPanda.rotation = 180f
                        }
                        90f -> {
                            (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE * sizeElements
                            (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE * sizeElements
                            myPanda.rotation = 180f
                        }
                    }
                }
                CORNER_RIGHT -> {
                    when (myPanda.rotation){
                        0f -> {
                            (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin -= CELL_SIZE * sizeElements
                            (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE * sizeElements
                            myPanda.rotation = 90f
                        }
                        180f -> {
                            (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE * sizeElements
                            (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE * sizeElements
                            myPanda.rotation = 90f
                        }

                        270f -> {
                            (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin -= CELL_SIZE * sizeElements
                            (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin -= CELL_SIZE * sizeElements
                            myPanda.rotation = 0f
                        }
                        90f -> {
                            (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin -= CELL_SIZE * sizeElements
                            (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE * sizeElements
                            myPanda.rotation = 0f
                        }
                    }
                }
                EAT -> {
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
                if (checkPandaCanMoveThroughBorder(
                        nextCoordinate,
                        myPanda
                    ) && checkPandaCanMoveThroughMaterial(nextCoordinate, elementsOnContainer)
                ) {
                    container.removeView(myPanda)
                    container.addView(myPanda)
                } else {
                    list.stopGame(true)
                    (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin =
                        currentCoordinate.top
                    (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin =
                        currentCoordinate.left
                }
    }

    ////////// удаление бамбука
    private fun compareCollection(elementsOnContainer: MutableList<Elements>, coordinateList: List<Coordinate>){
        coordinateList.forEach {
            val element = getElementByCoordinate(it, elementsOnContainer)
            removeElement(element)
        }
        numberBamboo -= 1
    }

//    private fun removeBambooContainer(element: Elements?,) {
//        if (element != null && element.material == Material.BAMBOO){
//            removeElement(element)
//        }
//    }

    private fun removeElement(element: Elements?) {
   //     activity.runOnUiThread {
        if (element != null && element.material == Material.BAMBOO){
            val view = ImageView(container.context)
            val layoutParams = FrameLayout.LayoutParams(CELL_SIZE * sizeElements, CELL_SIZE * sizeElements)
            view.layoutParams = layoutParams
            view.setImageResource(R.drawable.tree)
            layoutParams.topMargin = element.coordinateX
            layoutParams.leftMargin= element.coordinateY
            container.addView(view)
            container.removeView(activity.findViewById(element.viewId))
        }
   //}
    }
////////////


    private fun checkPandaCanMoveThroughMaterial(coordinate: Coordinate, elementsOnContainer: List<Elements>):Boolean{
        getPandaCoordinates(coordinate).forEach {
            val element = getElementByCoordinate(it, elementsOnContainer)
            if (element != null && element.material.pandaCanGoThrough){
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

