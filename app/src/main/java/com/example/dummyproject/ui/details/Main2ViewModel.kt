package com.example.dummyproject.ui.details

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
class Main2ViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private var _productResponse: MutableLiveData<NetworkResult<Products>> =
        MutableLiveData()
    val productResponse: LiveData<NetworkResult<Products>> =
        _productResponse


    fun getProduct(id:String) = coroutinesScope.launch {
        getProductSafeCall(id = id)
    }


    private suspend fun getProductSafeCall(id:String) {
        _productResponse.postValue(NetworkResult.Loading())
        if (Utils.hasInternetConnection()) {
            try {
                val response = repository.remote.getProduct(id)
                _productResponse.postValue(NetworkResult.Success(response))

            } catch (e: Exception) {
                _productResponse.postValue(NetworkResult.Error("Product not found."))
            }
        } else {
            _productResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }





}