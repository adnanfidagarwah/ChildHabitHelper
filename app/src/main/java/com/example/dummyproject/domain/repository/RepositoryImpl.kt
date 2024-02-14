package com.example.dummyproject.domain.repository

import com.example.dummyproject.data.local.LocalDataSource
import com.example.dummyproject.data.local.database.child.Child
import com.example.dummyproject.data.local.database.goal.Goal
import com.example.dummyproject.data.local.database.goal_category.GoalCategory
import com.example.dummyproject.data.remote.network.NetworkApi
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    remoteDataSource: NetworkApi,
    localDataSource: LocalDataSource
) : Repository {

    val remote = remoteDataSource
    val local = localDataSource

    override suspend fun loadChild(): List<Child> {
        return try {
            local.readChild()
        } catch (e: Exception) {
            listOf()
        }
//        return flow {

//
//            try {
//                remote.loadRepositories().apply {
//                    if (this.isSuccessful)
//                        emit(NetworkResult.Success(data = this.body()!!))
//                    else
//                        NetworkResult.Error(message = this.errorBody().toString(), data = null)
//                }
//
//            } catch (e: Exception) {
//                emit(NetworkResult.Error(message = e.message.toString(), data = null))
//            }

//        }
    }

    override suspend fun insertChild(child: Child) {
        local.insertChild(child)
    }

    override suspend fun updateChild(child: Child) {
        local.updateChild(child)
    }

    override suspend fun getChildById(uid: Int): Child {
        return local.getChildById(uid)
    }


    override suspend fun getGoalCategory(): List<GoalCategory> {
        return try {
            local.readGoalCategory()
        } catch (e: Exception) {
            listOf()
        }
    }

    override suspend fun getGoal(): List<Goal> {
        return try {
            local.readGoal()
        } catch (e: Exception) {
            listOf()
        }
    }

}