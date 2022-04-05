package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.level.LevelItem
import com.example.myapplication.level.StepItem

class StepAdapter(context: Context): RecyclerView.Adapter<StepAdapter.StepHolder>() {
    var listArray = ArrayList<StepItem>()

    private var events = listArray
    private var appContext = context

    class StepHolder(view: View): RecyclerView.ViewHolder(view) {
        private val stepView = view.findViewById<ImageView>(R.id.stepView)
        fun bind(step: StepItem){
            stepView.setImageResource(step.stepView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepHolder {
        val inflater = LayoutInflater.from(appContext).inflate(R.layout.step_shablon, parent, false)
        return StepHolder(inflater)
    }

    override fun onBindViewHolder(holder: StepHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int {
        return events.size
    }

    fun addStep(step: StepItem){
        events.add(step)
        notifyDataSetChanged()
    }
}