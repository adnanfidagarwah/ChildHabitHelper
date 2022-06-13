package com.example.dummyproject.data.database.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dummyproject.ui.model.RepositoriesResponse
import com.example.dummyproject.util.Constants.Companion.REPOSITORIES_TABLE

@Entity(tableName = REPOSITORIES_TABLE)
class RepositoriesEntity(
    var repositoriesResponse: RepositoriesResponse
) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}