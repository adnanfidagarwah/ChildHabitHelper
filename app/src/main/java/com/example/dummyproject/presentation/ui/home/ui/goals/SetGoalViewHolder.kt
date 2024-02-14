package com.example.dummyproject.presentation.ui.home.ui.goals

import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.data.local.database.goal.Goal
import com.example.dummyproject.databinding.GoalItemDataBinding
import com.example.dummyproject.databinding.SetGoalItemDataBinding

class SetGoalViewHolder(
    val binding: SetGoalItemDataBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(goal: Goal) {
        binding.heading.text = goal.name
        binding.unit.text = goal.value.toString().plus(" ").plus(goal.unit)
        binding.points.text = goal.points.toString().plus(" Points")
        binding.goalIcon.setImageResource(goal.icon)
    }
}

/*
override fun onClick(position: Int) {
    if (position == profileList.size - 1) {
      Util.showToast("Clicked on Add Profile")

    } else {
        Util.showToast("Clicked on Profile")
    }
}*/
