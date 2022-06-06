package com.example.myapplication.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.delete_dialog.*

interface OnDeleteLevelClickListener{
    fun onDeleteLevel(boolean: Boolean)
}

class DeleteDialog(listener:OnDeleteLevelClickListener): DialogFragment(){
    private var list = listener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return inflater.inflate(R.layout.delete_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCanceledOnTouchOutside(false)
        onClick()
    }

    private fun onClick(){
        deleteLevel.setOnClickListener {
            list.onDeleteLevel(true)
            dialog?.hide()
        }
        hideDialog.setOnClickListener {
            dialog?.hide()
        }
    }
}