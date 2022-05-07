package com.example.myapplication.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.size_material.*

interface OnSizeElementsButton{
    fun elementsSize(size: Int)
}

class SizeMaterial(listener: OnSizeElementsButton): DialogFragment() {
    private val sizeClickListener = listener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return inflater.inflate(R.layout.size_material, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCanceledOnTouchOutside(false)
        onClick()
    }

    @SuppressLint("ResourceType")
    private fun onClick(){
        viewLittle.setOnClickListener {
            sizeClickListener.elementsSize(2)
            context?.resources?.getColor(R.color.text_subtitle, null)
                ?.let { it1 -> viewLittle.background.setTint(it1) }
            context?.resources?.getColor(R.color.gray, null)
                ?.let { it1 -> viewAverage.background.setTint(it1) }
        }
        viewAverage.setOnClickListener {
            sizeClickListener.elementsSize(3)
            context?.resources?.getColor(R.color.text_subtitle, null)
                ?.let { it1 -> viewAverage.background.setTint(it1) }
            context?.resources?.getColor(R.color.gray, null)
                ?.let { it1 -> viewLittle.background.setTint(it1) }
        }


        viewExit.setOnClickListener {
            dialog?.hide()
        }
    }
}