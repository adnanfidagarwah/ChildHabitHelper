package com.example.dummyproject.data.repository

import com.example.dummyproject.ui.model.RepositoriesResponse
import com.example.dummyproject.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    suspend fun loadRepositories():  Flow<NetworkResult<RepositoriesResponse>>
}
