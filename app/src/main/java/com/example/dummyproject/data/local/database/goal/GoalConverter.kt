package com.example.dummyproject.data.local.database.goal

import androidx.room.TypeConverter
import com.example.dummyproject.presentation.util.observer.toJson
import com.example.dummyproject.presentation.util.observer.toObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GoalConverter {

    val gson = Gson()

    @TypeConverter
    fun goalToString(goal: Goal): String =
        goal.toJson()

    @TypeConverter
    fun stringToGoal(data: String): Goal = data.toObject()


    @TypeConverter
    fun itemToString(item: List<Goal>): String = item.toJson()

    @TypeConverter
    fun stringToItem(data: String): List<Goal> {
        val myType = object : TypeToken<List<Goal>>() {}.type
        return gson.fromJson(data, myType)
    }
}