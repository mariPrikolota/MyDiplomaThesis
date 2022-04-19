package com.example.myapplication.drawers


import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.myapplication.*
import com.example.myapplication.bd.Elements
import com.example.myapplication.enums.Material
import com.example.myapplication.models.Coordinate



class ElementsDrawer(val container: FrameLayout) {
    var currentMaterial = Material.EMPTY
    val elementsOnContainer = mutableListOf<Elements>()

    fun onTouchContainer(x:Float, y:Float){
        val topMargin = y.toInt() - (y.toInt()% 120)
        val leftMargin = x.toInt() - (x.toInt()% 120)
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
            val erasingView = container.findViewById<View>(elementOnCoordinate.id)
            container.removeView(erasingView)
            elementsOnContainer.remove(elementOnCoordinate)
        }
    }

    private fun drawView(coordinateX: Int, coordinateY: Int){
        val view = ImageView(container.context)
        val layoutParams = FrameLayout.LayoutParams(120, 120)
        when(currentMaterial){
            Material.STONE -> view.setImageResource(R.drawable.stone)
            Material.TREE -> view.setImageResource(R.drawable.tree)
            Material.BAMBOO -> view.setImageResource(R.drawable.bamboo)
            Material.PANDA -> view.setImageResource(R.drawable.panda_stop)
        }
        layoutParams.topMargin = coordinateX
        layoutParams.leftMargin= coordinateY
        val viewId = View.generateViewId()
        view.id = viewId
        view.layoutParams = layoutParams
        container.addView(view)
        elementsOnContainer.add(Elements(viewId, currentMaterial,coordinateX,coordinateY))

    }

    fun drawElementsList(elements: List<Elements>?){
        if (elements == null){
            return
        }
        for (element in elements){
            currentMaterial= element.material
            drawView( element.coordinateX, element.coordinateY)
        }
    }
}