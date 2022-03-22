package com.example.myapplication


import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.drawers.ElementsDrawer
import com.example.myapplication.enums.Direction.*
import com.example.myapplication.drawers.GridDrawer
import com.example.myapplication.enums.Direction
import com.example.myapplication.enums.Material
import com.example.myapplication.models.Coordinate
import kotlinx.android.synthetic.main.game_layout.*

const val CELL_SIZE = 60
const val HORIZONTAL_CELL_AMOUNT = 24
const val VERTICAL_CELL_AMOUNT = 25
const val MAX_VERTICAL = VERTICAL_CELL_AMOUNT * CELL_SIZE
const val MAX_HORIZONTAL = HORIZONTAL_CELL_AMOUNT * CELL_SIZE

class GamePlayer: AppCompatActivity(), View.OnClickListener {
    private lateinit var myPanda: ImageView
    private var editMode = false

    private val gridDrawer by lazy {
        GridDrawer(container)
    }

    private val elementDrawer by lazy {
        ElementsDrawer(container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)
        onKeyButton()
        myPanda = findViewById(R.id.myPanda)
        container.layoutParams = FrameLayout.LayoutParams(MAX_VERTICAL, MAX_HORIZONTAL)
        stoneView.setOnClickListener { elementDrawer.currentMaterial = Material.STONE }
        treeView.setOnClickListener { elementDrawer.currentMaterial = Material.TREE }
        bambooView.setOnClickListener { elementDrawer.currentMaterial = Material.BAMBOO }
        container.setOnTouchListener { _, motionEvent ->
            elementDrawer.onTouchContainer(motionEvent.x, motionEvent.y)
            return@setOnTouchListener true
        }

    }

    private fun onKeyButton(){
        upView.setOnClickListener(this)
        downView.setOnClickListener(this)
        leftView.setOnClickListener(this)
        rightView.setOnClickListener(this)
        eatView.setOnClickListener(this)
        functionView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.upView -> move(UP)
            R.id.downView -> move(DOWN)
            R.id.leftView -> move(LEFT)
            R.id.rightView -> move(RIGHT)
            R.id.eatView -> {
                switchEditMode()
            }
            R.id.functionView -> {
                switchEditMode()
            }
        }
    }

    private fun switchEditMode(){
        if (editMode){
            gridDrawer.removeGrid()
            materialContainer.visibility = View.GONE
            stepContainer.visibility = View.VISIBLE
        }else{
            gridDrawer.drawGrid()
            materialContainer.visibility = View.VISIBLE
            stepContainer.visibility = View.GONE
        }
        editMode = !editMode
    }

    private fun move(direction: Direction){
        myPanda.setBackgroundResource(R.drawable.panda_top)
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

            }
        }
        container.removeView(myPanda)
        container.addView(myPanda)
    }
}