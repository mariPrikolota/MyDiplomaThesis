package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.level.StepItem

class StepAdapter(listArray: ArrayList<StepItem>): RecyclerView.Adapter<StepAdapter.StepHolder>() {
    private var events = listArray
//    private val context = context

    class StepHolder(view: View): RecyclerView.ViewHolder(view) {
        private val stepView = view.findViewById<ImageView>(R.id.stepView)
        fun bind(step: StepItem){
            stepView.setImageResource(step.stepView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.step_shablon, parent, false)
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