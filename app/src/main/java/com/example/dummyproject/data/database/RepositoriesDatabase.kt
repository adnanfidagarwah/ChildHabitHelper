package com.example.dummyproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dummyproject.data.database.entites.RepositoriesEntity
import com.example.dummyproject.ui.model.RepositoriesResponse

@Database(
    entities = [
        RepositoriesEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RepositoryTypeConverter::class)
abstract class RepositoriesDatabase: RoomDatabase() {

    abstract fun repositoriesDao(): RepositoriesDao
}