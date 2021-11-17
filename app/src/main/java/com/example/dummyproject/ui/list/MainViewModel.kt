package com.example.dummyproject.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dummyproject.base.BaseViewModel
import com.example.dummyproject.data.Repository
import com.example.dummyproject.ui.list.model.Products
import com.example.dummyproject.util.NetworkResult
import com.example.dummyproject.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private var _productsResponse: MutableLiveData<NetworkResult<ArrayList<Products>>> =
        MutableLiveData()
    val productsResponse: LiveData<NetworkResult<ArrayList<Products>>> =
        _productsResponse


    init {
        getProducts()
    }

    private fun getProducts() = coroutinesScope.launch {
        getProductsSafeCall()
    }


    private suspend fun getProductsSafeCall() {
        _productsResponse.postValue(NetworkResult.Loading())
        if (Utils.hasInternetConnection()) {
            try {
                val response = repository.remote.getProducts()
                _productsResponse.postValue(NetworkResult.Success(response))

            } catch (e: Exception) {
                _productsResponse.postValue(NetworkResult.Error("Products not found."))
            }
        } else {
            _productsResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }


}