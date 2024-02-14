package com.example.dummyproject.presentation.ui.home.ui.goals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.data.local.database.goal.Goal
import com.example.dummyproject.databinding.SetGoalItemDataBinding

class SetGoalsAdapter(val goals: List<Goal>) : RecyclerView.Adapter<SetGoalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetGoalViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SetGoalItemDataBinding.inflate(layoutInflater, parent, false)
        return SetGoalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SetGoalViewHolder, position: Int) {
        holder.bind(goals[position])
    }


    override fun getItemCount(): Int {
        return goals.size
    }


}