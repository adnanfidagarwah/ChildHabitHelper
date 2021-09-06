package com.example.dummyproject.data

import com.example.dummyproject.network.NetworkApi
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {

    suspend fun getRecipes(queries: JSONObject): Response<JSONObject> {
        return networkApi.getRecipes(queries)
    }



}