package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.game_over_layout.*

interface OnGameOverDialogButtonClickListener{
    fun onGameAgainClickListener(boolean: Boolean)
}

class GameOver(listener: OnGameOverDialogButtonClickListener): DialogFragment(){
    private val againClickListener = listener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return inflater.inflate(R.layout.game_over_layout, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCanceledOnTouchOutside(false)
        onClick()
    }

     private fun onClick(){
        again.setOnClickListener {
            againClickListener.onGameAgainClickListener(true)
            Thread.sleep(300)
            dialog?.hide()
        }
    }
}
