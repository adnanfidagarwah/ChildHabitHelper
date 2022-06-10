package com.example.dummyproject.data

import com.example.dummyproject.base.BaseResponse
import com.example.dummyproject.network.NetworkApi
import org.json.JSONObject
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {
    suspend fun getRepositories(): BaseResponse<JSONObject> {
        return networkApi.getRepositories()
    }


}