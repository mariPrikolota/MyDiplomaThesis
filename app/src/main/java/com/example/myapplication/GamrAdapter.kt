package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class GamrAdapter(listArray: ArrayList<ListItem>, context: Context): RecyclerView.Adapter<GamrAdapter.ViewHolder>(){
    private var events = listArray
    private var appContext = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val quest = view.findViewById<TextView>(R.id.questText)

        fun bind(listItem: ListItem, context: Context){
            quest.text = listItem.quest
            itemView.setOnClickListener() {
                Toast.makeText(context, "${quest.text}", Toast.LENGTH_SHORT).show()
                val  intent = Intent(context, GameLayout::class.java).apply {  }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(appContext)
        return ViewHolder(inflater.inflate(R.layout.shablon_game_layout, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position],appContext)
    }

    override fun getItemCount(): Int {
        return events.size
    }

}