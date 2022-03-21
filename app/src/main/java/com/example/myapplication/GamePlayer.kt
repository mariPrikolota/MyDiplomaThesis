package com.example.myapplication


import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Direction.*
import com.example.myapplication.Level.GridDrawer
import kotlinx.android.synthetic.main.game_layout.*

const val CELL_SIZE = 60
const val HORIZONTAL_CELL_AMOUNT = 24
const val VERTICAL_CELL_AMOUNT = 25
const val MAX_VERTICAL = VERTICAL_CELL_AMOUNT * CELL_SIZE
const val MAX_HORIZONTAL = HORIZONTAL_CELL_AMOUNT * CELL_SIZE

class GamePlayer: AppCompatActivity(), View.OnClickListener {
    private lateinit var myPanda: ImageView
    private val gridDrawer by lazy {
        GridDrawer(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)
        onKeyButton()
        myPanda = findViewById(R.id.myPanda)
        container.layoutParams = FrameLayout.LayoutParams(MAX_VERTICAL, MAX_HORIZONTAL)
    }

    private fun onKeyButton(){
        forwardView.setOnClickListener(this)
        leftView.setOnClickListener(this)
        rightView.setOnClickListener(this)
        eatView.setOnClickListener(this)
        functionView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.forwardView -> move(UP)
            R.id.leftView -> move(LEFT)
            R.id.rightView -> move(RIGHT)
            R.id.eatView -> move(EAT)
            R.id.functionView -> {
                gridDrawer.drawGrid()
            }
        }
    }

    private fun move(direction: Direction){
        myPanda.setBackgroundResource(R.drawable.panda_top)
        when(direction){
            UP -> {
                myPanda.rotation = 180f
                (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin += 50
            }
            LEFT -> {
                myPanda.rotation = 270f
                (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin -= 50
            }
            RIGHT -> {
                myPanda.rotation = 90f
                (myPanda.layoutParams as FrameLayout.LayoutParams).leftMargin += 50
            }
            EAT -> {
                myPanda.rotation = 0f
                (myPanda.layoutParams as FrameLayout.LayoutParams).topMargin += -50
            }
        }
        container.removeView(myPanda)
        container.addView(myPanda)
    }


}