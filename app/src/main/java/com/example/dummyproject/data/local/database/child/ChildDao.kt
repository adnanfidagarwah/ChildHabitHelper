package com.example.dummyproject.data.local.database.child

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ChildDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChild(child: Child)

    @Query("SELECT * FROM Child")
    fun readChild(): List<Child>


//    @Query("UPDATE Child SET name=:name, image=:image WHERE uid = :id")
//    fun updateChild(name: String, image: Bitmap, id:Int)

    @Update
    fun updateChild(child: Child)


    @Query("SELECT * FROM Child WHERE uid = :uid")
    fun getChildById(uid:Int): Child


}