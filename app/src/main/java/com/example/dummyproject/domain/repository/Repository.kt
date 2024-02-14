package com.example.dummyproject.domain.repository

import com.example.dummyproject.data.local.database.child.Child
import com.example.dummyproject.data.local.database.goal.Goal
import com.example.dummyproject.data.local.database.goal_category.GoalCategory

interface Repository {
    suspend fun loadChild():  List<Child>

    suspend fun insertChild(child: Child)

    suspend fun updateChild(child: Child)

    suspend fun getChildById(uid: Int): Child

    suspend fun getGoalCategory():  List<GoalCategory>

    suspend fun getGoal():  List<Goal>
}
