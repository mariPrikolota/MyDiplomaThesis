package com.example.myapplication.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R

class ForgotPanda: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return inflater.inflate(R.layout.forgot_panda, container, false)
    }
}