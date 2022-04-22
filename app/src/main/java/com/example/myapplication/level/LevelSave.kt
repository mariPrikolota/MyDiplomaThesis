package com.example.myapplication.level

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.bd.Elements
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.prefs.PreferenceChangeEvent
import java.util.prefs.Preferences

const val KEY_LEVEL = "key_level"

class LevelSave(private val context: Context) {
      val prefs = (context as Activity).getPreferences(Context.MODE_PRIVATE)

    fun saveLevel(elementsOnContainer: List<Elements>): String {
        val gson =  Gson().toJson(elementsOnContainer)
        prefs.edit()
            .putString(KEY_LEVEL,gson)
            .apply().toString()

        return gson
    }

    fun loadLevel(level: String): List<Elements>?{
        val levelFromPrefs = prefs.getString(KEY_LEVEL, null)
//        val levelFromPrefs = Gson().toJson(level)
        level.let {
            val type = object : TypeToken<List<Elements>>(){}.type
            return Gson().fromJson(it,type)
        }
    }
}