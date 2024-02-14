package com.example.dummyproject.data.local.database.goal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(vararg goal: Goal)

    @Query("SELECT * FROM Goal")
    fun readGoal(): List<Goal>


//    @Query("UPDATE Child SET name=:name, image=:image WHERE uid = :id")
//    fun updateChild(name: String, image: Bitmap, id:Int)

    @Update
    fun updateGoal(child: Goal)


    @Query("SELECT * FROM Goal WHERE uid = :uid")
    fun getGoalById(uid: Int): Goal


}