package com.example.dummyproject.network

import com.example.dummyproject.base.BaseResponse
import com.example.dummyproject.util.Constants
import org.json.JSONObject
import retrofit2.http.GET

interface NetworkApi {


    @GET(Constants.REPOSITORIES_API)
    suspend fun getRepositories(): BaseResponse<JSONObject>

}