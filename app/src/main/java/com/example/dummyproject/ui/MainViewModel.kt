package com.example.dummyproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dummyproject.base.BaseViewModel
import com.example.dummyproject.data.Repository
import com.example.dummyproject.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private var _repositoriesResponse: MutableLiveData<NetworkResult<JSONObject>> =
        MutableLiveData()
    val repositoriesResponse: LiveData<NetworkResult<JSONObject>> =
        _repositoriesResponse


    init {
        getRepositories()
    }

    private fun getRepositories() = coroutinesScope.launch {
        getRepositoriesSafeCall()
    }


    private suspend fun getRepositoriesSafeCall() {
        _repositoriesResponse.postValue(NetworkResult.Loading())
        try {
            val response = repository.remote.getRepositories()
            _repositoriesResponse.postValue(NetworkResult.Success(response.data))

        } catch (e: Exception) {
            _repositoriesResponse.postValue(NetworkResult.Error(e.message.toString()))
        }
    }
}

