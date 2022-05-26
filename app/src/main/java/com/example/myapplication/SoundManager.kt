package com.example.myapplication

import android.content.Context
import android.media.MediaPlayer

class SoundManager(context: Context) {
    private val gameSoundFirst = MediaPlayer.create(context, R.raw.gitara)
    private val gameSoundSecond = MediaPlayer.create(context, R.raw.gitara)

    init {
        gameSound()
    }

    private fun gameSound(){
        gameSoundFirst.isLooping = true
        gameSoundSecond.isLooping = true

    }

    fun soundStart(){
        gameSoundFirst.start()
    }

    fun soundStop(){
        if (gameSoundFirst.isPlaying){
            gameSoundFirst.pause()
        }
        if (gameSoundSecond.isPlaying){
            gameSoundSecond.pause()
        }
    }
}