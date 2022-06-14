package com.example.dummyproject.data

import com.example.dummyproject.data.database.RepositoriesDao
import com.example.dummyproject.ui.model.RepositoriesResponse
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val repositoriesDao: RepositoriesDao
) {

    fun readRepositories(): RepositoriesResponse {
        return repositoriesDao.readRepositories()
    }

    suspend fun insertRepositories(repositoriesResponse: RepositoriesResponse) {
        repositoriesDao.insertRepositories(repositoriesResponse)
    }
}
