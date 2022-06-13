package com.example.dummyproject.data

import com.example.dummyproject.data.database.RepositoriesDao
import com.example.dummyproject.data.database.entites.RepositoriesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val repositoriesDao: RepositoriesDao
) {

    fun readRepositories(): Flow<List<RepositoriesEntity>> {
        return repositoriesDao.readRepositories()
    }

    suspend fun insertRepositories(repositoriesEntity: RepositoriesEntity) {
        repositoriesDao.insertRepositories(repositoriesEntity)
    }
}
