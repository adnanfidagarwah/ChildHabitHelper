package com.example.dummyproject.data.local

import com.example.dummyproject.data.local.database.child.Child
import com.example.dummyproject.data.local.database.child.ChildDao
import com.example.dummyproject.data.local.database.goal.Goal
import com.example.dummyproject.data.local.database.goal.GoalDao
import com.example.dummyproject.data.local.database.goal_category.GoalCategory
import com.example.dummyproject.data.local.database.goal_category.GoalCategoryDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val childDao: ChildDao,
    private val goalCategoryDao: GoalCategoryDao,
    private val goalDao: GoalDao
) {

    fun readChild(): List<Child> {
        return childDao.readChild()
    }

    suspend fun insertChild(child: Child) {
        childDao.insertChild(child)
    }

    suspend fun updateChild(child: Child) {
        childDao.updateChild(child)
    }

    suspend fun getChildById(uid: Int): Child {
        return childDao.getChildById(uid)
    }

    fun readGoalCategory(): List<GoalCategory> {
        return goalCategoryDao.readGoalCategory()
    }

    fun readGoal(): List<Goal> {
        return goalDao.readGoal()
    }
}
