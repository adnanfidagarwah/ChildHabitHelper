package com.example.dummyproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dummyproject.data.database.entites.RepositoriesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RepositoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositoriesEntity: RepositoriesEntity)

    @Query("SELECT * FROM repositories_table")
    fun readRepositories(): Flow<List<RepositoriesEntity>>


}