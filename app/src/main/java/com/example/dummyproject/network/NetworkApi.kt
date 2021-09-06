package com.example.dummyproject.network

import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface NetworkApi {

    @POST("/recipes/complexSearch")
    suspend fun getRecipes(
        @Body body: JSONObject
    ): Response<JSONObject>
}