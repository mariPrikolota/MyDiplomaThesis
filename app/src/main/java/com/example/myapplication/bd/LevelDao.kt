package com.example.myapplication.bd

import androidx.room.*

@Dao
interface LevelDao {

    @Query("SELECT * FROM level")
    fun getAllLevel(): List<Level>?

    @Insert
    fun insertLevel(event: Level?)

    @Delete
    fun deleteLevel(event: Level?)

    @Update
    fun updateLevel(event: Level?)
}