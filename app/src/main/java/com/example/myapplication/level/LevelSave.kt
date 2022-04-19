package com.example.myapplication.level

import android.app.Activity
import android.content.Context

const val KEY_LEVEL = "key_level"

class LevelSave(private val context: Context) {
    private val prefs = (context as Activity).getPreferences(Context.MODE_PRIVATE)
//
//    fun saveLevel(elementsOnContainer: List<Elements>){
//        prefs.edit()
//            .putString(KEY_LEVEL, Gson().toJson(elementsOnContainer))
//            .apply()
//    }
//
//    fun loadLevel(): List<Elements>?{
//        val levelFromPrefs = prefs.getString(KEY_LEVEL, null)
//        levelFromPrefs.let {
//            val type = object : TypeToken<List<Elements>>(){}.type
//            return Gson().fromJson(it,type)
//
//        }
//    }
}