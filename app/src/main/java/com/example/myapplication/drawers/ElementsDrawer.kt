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
        if (viewOnCoordinate.material != currentMaterial.toString()){
            replaceView(coordinate)
        }
    }

    private fun  getElementByCoordinate(coordinate: Coordinate) = elementsOnContainer.firstOrNull { it.coordinateX == coordinate.top && it.coordinateY == coordinate.left }

    private fun replaceView(coordinate: Coordinate){
        eraseView(coordinate)
        drawView(coordinate)
    }

      private fun eraseView(coordinate: Coordinate){
        val elementOnCoordinate = getElementByCoordinate(coordinate)
        if (elementOnCoordinate != null){
            val erasingView = container.findViewById<View>(elementOnCoordinate.id)
            container.removeView(erasingView)
            elementsOnContainer.remove(elementOnCoordinate)
        }
    }

    private fun drawView(coordinate: Coordinate){
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
        elementsOnContainer.add(Elements(viewId, currentMaterial.toString(), coordinate.top, coordinate.left))

    }

//    fun drawElementsList(elements: List<Elements>?){
//        if (elements == null){
//            return
//        }
//        for (element in elements){
//            currentMaterial= element.material
//            drawView(element.)
//        }
//    }
}