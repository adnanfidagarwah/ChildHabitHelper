package com.example.dummyproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dummyproject.base.BaseViewModel
import com.example.dummyproject.data.Repository
import com.example.dummyproject.ui.model.RepositoriesResponse
import com.example.dummyproject.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {



    /**======== repositories Response MutableLiveData ========*/
    private var _repositoriesResponse: MutableLiveData<NetworkResult<RepositoriesResponse>> =
        MutableLiveData()
    val repositoriesResponse: LiveData<NetworkResult<RepositoriesResponse>> =
        _repositoriesResponse


    init {
        getRepositories()
    }

    /**======== API call Methods ========*/

    /**======== get Repositories API call Methods ========*/
    fun getRepositories() = coroutinesScope.launch {
        getRepositoriesSafeCall()
    }


    private suspend fun getRepositoriesSafeCall() {
        _repositoriesResponse.postValue(NetworkResult.Loading())
        try {
            val response = repository.remote.getRepositories()
            if (response.isSuccessful)
                response.body()?.let {
                    _repositoriesResponse.postValue(NetworkResult.Success(it))
                }
            else
                _repositoriesResponse.postValue(NetworkResult.Error(response.message().toString()))

        } catch (e: Exception) {
            _repositoriesResponse.postValue(NetworkResult.Error(e.message.toString()))
        }
    }
}

