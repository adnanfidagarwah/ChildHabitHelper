package com.example.dummyproject.network

import com.example.dummyproject.ui.model.RepositoriesResponse
import com.example.dummyproject.util.Constants
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {

    @GET(Constants.REPOSITORIES_API)
    suspend fun loadRepositories(): Response<RepositoriesResponse>

}