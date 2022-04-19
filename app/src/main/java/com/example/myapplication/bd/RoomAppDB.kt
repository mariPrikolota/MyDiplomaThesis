package com.example.myapplication.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Level::class, Elements::class], version = 1)
abstract class RoomAppDB : RoomDatabase() {
    abstract fun levelDao(): LevelDao?

    companion object {
        private var INSTANCE: RoomAppDB? = null

        fun getAppDB(context: Context): RoomAppDB? {
            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder<RoomAppDB>(
                    context.applicationContext, RoomAppDB::class.java, "AppDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}