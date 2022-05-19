package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer

@SuppressLint("StaticFieldLeak")
object SoundManager {
    private lateinit var gamePlayerFirst : MediaPlayer
    private lateinit var gamePlayerSecond : MediaPlayer
    private lateinit var stepPandaSound : MediaPlayer

    var context: Context? = null
        set(value) {
            stepPandaSound = MediaPlayer.create(value, R.raw.gitara)
            prepareGameSound(value!!)
        }

    private fun prepareGameSound(context: Context){
        gamePlayerFirst = MediaPlayer.create(context, R.raw.game)
        gamePlayerSecond = MediaPlayer.create(context, R.raw.game)
        gamePlayerFirst.isLooping = true
        gamePlayerSecond.isLooping = true
        gamePlayerFirst.setNextMediaPlayer(gamePlayerSecond)
        gamePlayerSecond.setNextMediaPlayer(gamePlayerFirst)
    }

    fun gameStart(){
        gamePlayerFirst.start()
    }
    fun stepSound(){
        stepPandaSound.start()
    }

    fun pauseSound(){
        if (gamePlayerFirst.isPlaying){
            gamePlayerFirst.pause()
        }
        if (gamePlayerSecond.isPlaying){
            gamePlayerSecond.pause()
        }
    }

}