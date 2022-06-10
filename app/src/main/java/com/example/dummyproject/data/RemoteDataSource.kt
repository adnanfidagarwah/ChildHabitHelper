package com.example.dummyproject.data

import com.example.dummyproject.network.NetworkApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {


}