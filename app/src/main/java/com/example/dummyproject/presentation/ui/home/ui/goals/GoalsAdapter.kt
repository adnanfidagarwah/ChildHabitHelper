package com.example.dummyproject.presentation.ui.home.ui.goals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.databinding.GoalItemDataBinding

class GoalsAdapter : RecyclerView.Adapter<GoalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GoalItemDataBinding.inflate(layoutInflater, parent, false)
        return GoalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bind()
    }


    override fun getItemCount(): Int {
        return 10
    }


}