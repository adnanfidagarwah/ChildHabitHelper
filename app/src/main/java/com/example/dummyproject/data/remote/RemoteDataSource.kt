package com.example.dummyproject.data.remote

import com.example.dummyproject.data.remote.network.NetworkApi

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {

//    suspend fun getRepositories(): Response<RepositoriesResponse> {
//        return networkApi.loadRepositories()
//    }

}