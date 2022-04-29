package com.example.myapplication.bd

import androidx.room.*

@Dao
interface LevelDao {

    @Query("SELECT * FROM level")
    fun getAllLevel(): List<Level>?

    @Insert
    fun insertLevel(event: Level?)

    @Query("DELETE FROM level WHERE Id = :id")
    fun deleteLevel(id: Int)

    @Update
    fun updateLevel(event: Level?)
}