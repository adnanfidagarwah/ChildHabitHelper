package com.example.dummyproject.data.local.database.goal_category

import androidx.room.TypeConverter
import com.example.dummyproject.presentation.util.observer.toJson
import com.example.dummyproject.presentation.util.observer.toObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GoalCategoryConverter {

    val gson = Gson()

    @TypeConverter
    fun goalCategoryToString(goalCategory: GoalCategory): String =
        goalCategory.toJson()

    @TypeConverter
    fun stringToGoalCategory(data: String): GoalCategory = data.toObject()


    @TypeConverter
    fun itemToString(item: List<GoalCategory>): String = item.toJson()

    @TypeConverter
    fun stringToItem(data: String): List<GoalCategory> {
        val myType = object : TypeToken<List<GoalCategory>>() {}.type
        return gson.fromJson(data, myType)
    }
}