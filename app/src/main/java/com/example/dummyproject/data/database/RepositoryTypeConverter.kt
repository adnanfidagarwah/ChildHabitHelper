package com.example.dummyproject.data.database

import androidx.room.TypeConverter
import com.example.dummyproject.ui.model.RepositoriesResponse
import com.example.dummyproject.util.observer.toJson
import com.example.dummyproject.util.observer.toObject

class RepositoryTypeConverter {

    @TypeConverter
    fun repositoriesToString(repositoriesResponse: RepositoriesResponse): String = repositoriesResponse.toJson()

    @TypeConverter
    fun stringTorepositories(data: String): RepositoriesResponse = data.toObject()

    @TypeConverter
    fun itemToString(result: RepositoriesResponse.Item): String = result.toJson()

    @TypeConverter
    fun stringToItem(data: String): RepositoriesResponse.Item {
        return data.toObject()
    }
}