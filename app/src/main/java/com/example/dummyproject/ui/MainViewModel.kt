package com.example.dummyproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dummyproject.base.BaseViewModel
import com.example.dummyproject.data.usecases.LoadRepositoriesUsecase
import com.example.dummyproject.ui.model.RepositoriesResponse
import com.example.dummyproject.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val loadRepositoriesUsecase: LoadRepositoriesUsecase) :
    BaseViewModel() {

    private var _uiState = MutableLiveData<MainUiState>()
    var uiStateLiveData: LiveData<MainUiState> = _uiState

    /**======== repositories Remote Database MutableLiveData ========*/
    private var _repositoriesResponse: MutableLiveData<RepositoriesResponse> =
        MutableLiveData()
    val repositoriesResponse: LiveData<RepositoriesResponse> =
        _repositoriesResponse


    init {
        getRepositories()
    }

    /**======== API call Methods ========*/

    /**======== get Repositories API call Methods ========*/
    fun getRepositories() = viewModelScope.launch(Dispatchers.IO) {

        loadRepositoriesUsecase().collect { response ->
            when (response) {
                is NetworkResult.Success -> {
                    _uiState.postValue(ContentState)
                    _repositoriesResponse.postValue(response.data)
                }
                is NetworkResult.Error -> {
                    _uiState.postValue(response.message?.let { ErrorState(it) })
                }
                is NetworkResult.Loading -> {
                    _uiState.postValue(LoadingState)
                }
            }
        }
    }


/*    private suspend fun getRepositoriesSafeCall() {
        _repositoriesResponse.postValue(NetworkResult.Loading())
        try {
            val response = repository.remote.getRepositories()
            if (response.isSuccessful)
                response.body()?.let {
                    offlineCacheRepositories(it)

                }
            else {
                readRepositoriesFromCache(response.message().toString())
            }


        } catch (e: Exception) {
            readRepositoriesFromCache(e.message.toString())
        }
    }*/

    /**======== save data in local database ========*/
/*
    private fun insertRepositories(
        repositoriesResponse: RepositoriesResponse
    ) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertRepositories(repositoriesResponse)
            _repositoriesResponse.postValue(NetworkResult.Success(data = repository.local.readRepositories()))
        }
*/


    /*private fun offlineCacheRepositories(repositoriesResponse: RepositoriesResponse) {
        insertRepositories(repositoriesResponse)
    }*/


    /**========read data from local database ========*/
    /* private fun readRepositoriesFromCache(message: String) =
         viewModelScope.launch(Dispatchers.IO) {
             _repositoriesResponse.postValue(
                 NetworkResult.Error(
                     message,
                     data = repository.local.readRepositories()
                 )
             )
         }*/

}

