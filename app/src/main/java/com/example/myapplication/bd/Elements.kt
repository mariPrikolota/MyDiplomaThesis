package com.example.myapplication.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.models.Coordinate

@Entity(tableName = "elements")
data class Elements(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "material") val material: String,
    @ColumnInfo(name = "coordinateX") val coordinateX: Int,
    @ColumnInfo(name = "coordinateY") val coordinateY: Int,
)

