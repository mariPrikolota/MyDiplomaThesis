package com.example.myapplication.drawers


import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.myapplication.*
import com.example.myapplication.bd.Elements
import com.example.myapplication.enums.Material
import com.example.myapplication.enums.Material.BAMBOO
import com.example.myapplication.models.Coordinate

var numberBamboo = 0

class ElementsDrawer(private val container: FrameLayout){
    var currentMaterial = Material.EMPTY
    val elementsOnContainer = mutableListOf<Elements>()
    var myPanda : ImageView? = null


    fun onTouchContainer(x:Float, y:Float){
        val topMargin = y.toInt() - (y.toInt()% (CELL_SIZE * sizeElements))
        val leftMargin = x.toInt() - (x.toInt()% (CELL_SIZE * sizeElements))
        val coordinate = Coordinate(topMargin, leftMargin)
        if(currentMaterial == Material.EMPTY){
            eraseView(topMargin, leftMargin)
        }else{
            drawOrReplaceView(topMargin, leftMargin)
        }
    }

    private fun drawOrReplaceView(coordinateX: Int, coordinateY: Int){
        val viewOnCoordinate = getElementByCoordinate(coordinateX, coordinateY)
        if (viewOnCoordinate == null){
            drawView(coordinateX, coordinateY)
            return
        }
        if (viewOnCoordinate.material != currentMaterial){
            replaceView(coordinateX, coordinateY)
        }
    }

    private fun  getElementByCoordinate(coordinateX: Int, coordinateY: Int) = elementsOnContainer.firstOrNull { it.coordinateX == coordinateX && it.coordinateY == coordinateY }

    private fun replaceView(coordinateX: Int, coordinateY: Int){
        eraseView(coordinateX, coordinateY)
        drawView(coordinateX, coordinateY)
    }

      private fun eraseView(coordinateX: Int, coordinateY: Int){
        val elementOnCoordinate = getElementByCoordinate(coordinateX, coordinateY)
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
                eraseView(erasingElements[0].coordinateX, erasingElements[0].coordinateY)
            }
        }
    }

    private fun drawView(coordinateX: Int, coordinateY: Int){
        removeUnwantedInstances()
        val view = ImageView(container.context)
            val layoutParams = FrameLayout.LayoutParams(CELL_SIZE * sizeElements, CELL_SIZE * sizeElements)
        layoutParams.topMargin = coordinateX
        layoutParams.leftMargin= coordinateY
        val viewId = View.generateViewId()
        view.id = viewId
        view.layoutParams = layoutParams
        when(currentMaterial){
            Material.STONE -> view.setImageResource(R.drawable.stone)
            Material.TREE -> view.setImageResource(R.drawable.tree)
            BAMBOO -> view.setImageResource(R.drawable.bamboo)
            Material.PANDA -> {
                view.setImageResource(R.drawable.panda_top)
                view.rotation = 180f
                myPanda = view
            }
        }
        container.addView(view)
        elementsOnContainer.add(Elements(viewId, currentMaterial,coordinateX,coordinateY, sizeElements))
    }


    fun drawElementsList(elements: List<Elements>?){
        if (elements == null){
            return
        }
        for (element in elements){
            currentMaterial= element.material
            sizeElements = element.width
            drawView( element.coordinateX, element.coordinateY)
            if (element.material == BAMBOO){
                numberBamboo += 1
            }
        }
        currentMaterial = Material.NULL
    }

}