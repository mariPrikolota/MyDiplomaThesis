package com.example.myapplication.level

import android.content.Context
import com.example.myapplication.bd.Elements
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LevelSave(private val context: Context) {

    fun saveLevel(elementsOnContainer: List<Elements>): String {
        return Gson().toJson(elementsOnContainer)
    }

    fun loadLevel(level: String): List<Elements>?{
        level.let {
            val type = object : TypeToken<List<Elements>>(){}.type
            return Gson().fromJson(it,type)
        }
    }
}