package com.example.dummyproject.data

import com.example.dummyproject.network.NetworkApi
import com.example.dummyproject.ui.list.model.Products
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {

    suspend fun getProducts(): ArrayList<Products> {
        return networkApi.getProducts()
    }

    suspend fun getProduct(id:String): Products {
        return networkApi.getProduct(id)
    }


}