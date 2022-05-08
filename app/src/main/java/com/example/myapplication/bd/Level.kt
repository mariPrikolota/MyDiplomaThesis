package com.example.myapplication.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "level")
data class Level(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
   val elementList: String,
)
