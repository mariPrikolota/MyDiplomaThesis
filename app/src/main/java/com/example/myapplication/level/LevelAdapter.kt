package com.example.myapplication.level

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

class LevelAdapter(listArray: ArrayList<LevelItem>, context: Context): RecyclerView.Adapter<LevelAdapter.ViewHolderGuide>(){
    private var events = listArray
    private var appContext = context

    class ViewHolderGuide (view: View) : RecyclerView.ViewHolder(view) {
        private val numberLevel = view.findViewById<TextView>(R.id.numberLever)
        fun bind(levelEvent: LevelItem, context: Context) {
            numberLevel.text = levelEvent.number.toString()
            itemView.setOnClickListener {
                val intent = Intent(context, GamePlayer::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGuide {
        val inflater = LayoutInflater.from(appContext)
        return ViewHolderGuide(inflater.inflate(R.layout.level_shablon, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderGuide, position: Int) {
        holder.bind(events[position],appContext)
    }

    override fun getItemCount(): Int {
        return events.size
    }

}