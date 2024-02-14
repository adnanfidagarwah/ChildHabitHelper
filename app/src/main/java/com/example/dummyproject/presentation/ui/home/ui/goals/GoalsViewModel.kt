package com.example.dummyproject.presentation.ui.home.ui.goals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dummyproject.data.local.database.goal.Goal
import com.example.dummyproject.data.local.database.goal_category.GoalCategory
import com.example.dummyproject.domain.repository.Repository
import com.example.dummyproject.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(val repository: Repository) :
    BaseViewModel() {


    /**======== get Goal Categories Success Response MutableLiveData ========*/
    private var _goalCategoryList: MutableLiveData<List<GoalCategory>> = MutableLiveData()
    val goalCategoryList: LiveData<List<GoalCategory>> get() = _goalCategoryList

    /**======== get Goal Success Response MutableLiveData ========*/
    private var _goalList: MutableLiveData<List<Goal>> = MutableLiveData()
    val goalList: LiveData<List<Goal>> get() = _goalList


    init {
        getAllGoalCategory()
        getAllGoal()
    }

    /**======== Database call Methods ========*/

    /**======== Get Goal Categories call Methods ========*/
    private fun getAllGoalCategory() = viewModelScope.launch {
        getAllGoalCategorySafeCall()
    }

    private suspend fun getAllGoalCategorySafeCall() {
        withContext(Dispatchers.IO) {
            try {
                val response = repository.getGoalCategory()
                _goalCategoryList.postValue(response)
            } catch (e: Exception) {
                _goalCategoryList.postValue(emptyList())
            }
        }
    }

    /**======== Get Goals call Methods ========*/
    private fun getAllGoal() = viewModelScope.launch {
        getAllGoalSafeCall()
    }

    private suspend fun getAllGoalSafeCall() {
        withContext(Dispatchers.IO) {
            try {
                val response = repository.getGoal()
                _goalList.postValue(response)
            } catch (e: Exception) {
                _goalList.postValue(emptyList())
            }
        }
    }
}