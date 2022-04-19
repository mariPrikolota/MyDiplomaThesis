package com.example.myapplication.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.enums.Material

@Entity(tableName = "elements")
data class Elements(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "material") val material: Material,
    @ColumnInfo(name = "coordinateX") val coordinateX: Int,
    @ColumnInfo(name = "coordinateY") val coordinateY: Int,
)

