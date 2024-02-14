package com.example.dummyproject.data.local.database.goal_category

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface GoalCategoryDao {

    @Upsert(GoalCategory::class)
    suspend fun insertGoalCategory(vararg goalCategory: GoalCategory)

    @Query("SELECT * FROM GoalCategory")
    fun readGoalCategory(): List<GoalCategory>

}