package com.example.myapplication


import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Direction.*
import kotlinx.android.synthetic.main.game_layout.*


class GamePlayer: AppCompatActivity(), View.OnClickListener {
    private lateinit var myPanda: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)
        onKeyButton()
        myPanda = findViewById(R.id.myPanda)
    }

    private fun onKeyButton(){
        forwardView.setOnClickListener(this)
        leftView.setOnClickListener(this)
        rightView.setOnClickListener(this)
        eatView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.forwardView -> move(UP)
            R.id.leftView -> move(LEFT)
            R.id.rightView -> move(RIGHT)
            R.id.eatView -> move(EAT)
            R.id.functionView -> {}
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