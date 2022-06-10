package com.example.dummyproject.data

import com.example.dummyproject.network.NetworkApi
import com.example.dummyproject.ui.model.RepositoriesResponse
import retrofit2.Response

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {

    suspend fun getRepositories(): Response<RepositoriesResponse> {
        return networkApi.getRepositories()
    }

}