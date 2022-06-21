package com.example.dummyproject.data.repository

import com.example.dummyproject.network.NetworkApi
import com.example.dummyproject.ui.model.RepositoriesResponse
import com.example.dummyproject.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    remoteDataSource: NetworkApi,
//    localDataSource: RepositoriesDao
) : Repository {

    val remote = remoteDataSource

    //    val local = localDataSource
    override suspend fun loadRepositories(): Flow<NetworkResult<RepositoriesResponse>> {
        return flow {
            try {
                remote.loadRepositories().apply {
                    if (this.isSuccessful)
                        emit(NetworkResult.Success(data = this.body()!!))
                    else
                        NetworkResult.Error(message = this.errorBody().toString(), data = null)
                }

            } catch (e: Exception) {
                emit(NetworkResult.Error(message = e.message.toString(), data = null))
            }

        }
    }

}