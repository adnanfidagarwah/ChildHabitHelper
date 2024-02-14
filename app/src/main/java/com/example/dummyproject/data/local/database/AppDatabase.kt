package com.example.dummyproject.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dummyproject.data.local.database.child.Child
import com.example.dummyproject.data.local.database.child.ChildDao
import com.example.dummyproject.data.local.database.child.ChildTypeConverter
import com.example.dummyproject.data.local.database.goal.Goal
import com.example.dummyproject.data.local.database.goal.GoalDao
import com.example.dummyproject.data.local.database.goal_category.GoalCategory
import com.example.dummyproject.data.local.database.goal_category.GoalCategoryConverter
import com.example.dummyproject.data.local.database.goal_category.GoalCategoryDao

@Database(
    entities = [
        Child::class,
        GoalCategory::class,
        Goal::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(ChildTypeConverter::class, GoalCategoryConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun childDao(): ChildDao

    abstract fun goalCategoryDao(): GoalCategoryDao

    abstract fun goalDao(): GoalDao
}