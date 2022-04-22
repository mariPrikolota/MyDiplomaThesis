package com.example.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.GamePlayer
import com.example.myapplication.R
import com.example.myapplication.bd.Level
import com.example.myapplication.bd.LevelDao



class LevelAdapter(listArray: List<Level>, context: Context): RecyclerView.Adapter<LevelAdapter.ViewHolderGuide>(){
    private var events = listArray
    private var appContext = context

    class ViewHolderGuide (view: View) : RecyclerView.ViewHolder(view) {
        private val numberLevel = view.findViewById<TextView>(R.id.numberLever)
        fun bind(levelEvent: Level, context: Context) {
            numberLevel.text = levelEvent.id.toString()
            itemView.setOnClickListener {
                val intent = Intent(context, GamePlayer::class.java).apply {
                   putExtra("level", levelEvent.elementList)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGuide {
        val inflater = LayoutInflater.from(appContext)
        return ViewHolderGuide(inflater.inflate(R.layout.level_shablon, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderGuide, position: Int) {
        val pos = events[position]
        holder.bind(pos,appContext)
    }

    override fun getItemCount(): Int {
        return events.size
    }

}