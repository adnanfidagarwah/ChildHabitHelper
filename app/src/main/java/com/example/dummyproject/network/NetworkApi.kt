package com.example.dummyproject.network

import com.example.dummyproject.ui.list.model.Products
import com.example.dummyproject.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApi {

    @GET(Constants.PRODUCTS_API)
    suspend fun getProducts(): ArrayList<Products>

    @GET(Constants.PRODUCT_API)
    suspend fun getProduct(@Path("id") id: String): Products
}