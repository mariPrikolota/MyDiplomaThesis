package com.example.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.GamePlayer
import com.example.myapplication.R
import com.example.myapplication.SoundManager
import com.example.myapplication.bd.Level
import com.example.myapplication.bd.LevelStudy

class LevelStudyAdapter( var listArray: List<LevelStudy>, var context: Context): RecyclerView.Adapter<LevelStudyAdapter.ViewHolderStudy>() {

    class ViewHolderStudy(view: View) : RecyclerView.ViewHolder(view) {
        private val numberLevel = view.findViewById<TextView>(R.id.numberLever)
        fun bind(levelEvent: LevelStudy, context: Context) {
            numberLevel.text = levelEvent.id.toString()
            itemView.setOnClickListener {
                val intent = Intent(context, GamePlayer::class.java).apply {
                    putExtra("levelStudy", levelEvent.elementList)
                    putExtra("idStudyLevel", levelEvent.id.toString())
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderStudy {
        val inflater = LayoutInflater.from(context)
        return LevelStudyAdapter.ViewHolderStudy(inflater.inflate(R.layout.level_shablon, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderStudy, position: Int) {
        holder.bind(listArray[position],context)
    }

    override fun getItemCount(): Int {
        return listArray.size
    }
}