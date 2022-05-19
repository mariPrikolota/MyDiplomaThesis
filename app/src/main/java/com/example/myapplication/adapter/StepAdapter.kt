package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.StepItem

class StepAdapter(): RecyclerView.Adapter<StepAdapter.StepHolder>() {
    private val events = ArrayList<StepItem>()

    class StepHolder(view: View) : RecyclerView.ViewHolder(view) {
         val stepView: ImageView = view.findViewById(R.id.stepView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.step_shablon, parent, false)
        return StepHolder(inflater)
    }

    override fun onBindViewHolder(holder: StepHolder, position: Int) {
        holder.stepView.setImageResource(events[position].stepView)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addStep(step: StepItem){
        events.add(step)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteStep(step: Int){
        events.removeAt(step)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteAllStep(){
        events.clear()
        notifyDataSetChanged()
    }
}