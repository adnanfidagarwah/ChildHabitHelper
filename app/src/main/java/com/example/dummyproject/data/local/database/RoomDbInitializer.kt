package com.example.dummyproject.data.local.database

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dummyproject.R
import com.example.dummyproject.data.local.database.goal.Goal
import com.example.dummyproject.data.local.database.goal.GoalDao
import com.example.dummyproject.data.local.database.goal_category.GoalCategory
import com.example.dummyproject.data.local.database.goal_category.GoalCategoryDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class RoomDbInitializer(
    private val goalCategoryProvider: Provider<GoalCategoryDao>,
    private val goalProvider: Provider<GoalDao>,
) : RoomDatabase.Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            populateDatabase()
        }
    }

    private suspend fun populateDatabase() {
        populateGaolCategories()
        populateGaols()
    }

    private suspend fun populateGaolCategories() {
        goalCategoryProvider.get()
            .insertGoalCategory(*goalCategoriesGenerator.take(4).toList().toTypedArray())
    }

    private suspend fun populateGaols() {
        goalProvider.get()
            .insertGoal(*goalGenerator.take(4).toList().toTypedArray())
    }
}

/**
 * This is a [Sequence] generator to generate goal categories.
 */
val goalCategoriesGenerator = listOf(
    GoalCategory(name = "Routines"),
    GoalCategory(name = "Activities"),
    GoalCategory(name = "Health"),
    GoalCategory(name = "Others")
)

val goalGenerator = listOf(
    Goal(
        goalCategoryId = 1,
        name = "Limit Screen Time",
        value = 30,
        unit = "Minutes",
        points = 20,
        icon = R.drawable.com_facebook_button_icon
    ),
    Goal(
        goalCategoryId = 2,
        name = "Painting",
        value = 4,
        unit = "Chart",
        points = 20,
        icon = R.drawable.com_facebook_button_icon
    ),
    Goal(
        goalCategoryId = 3,
        name = "Drink Water",
        value = 600,
        unit = "ml",
        points = 20,
        icon = R.drawable.com_facebook_button_icon
    ),
    Goal(
        goalCategoryId = 4,
        name = "Others",
        value = 3,
        unit = "others",
        points = 20,
        icon = R.drawable.com_facebook_button_icon
    ),
)
