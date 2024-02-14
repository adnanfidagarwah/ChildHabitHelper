package com.example.dummyproject.presentation.ui.home.ui.childs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dummyproject.data.local.database.child.Child
import com.example.dummyproject.domain.repository.Repository
import com.example.dummyproject.presentation.base.BaseViewModel
import com.example.dummyproject.presentation.util.observer.ClickEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChildsViewModel @Inject constructor(val repository: Repository) :
    BaseViewModel() {


    /**======== get Add Child Success Response MutableLiveData ========*/
    private var  _childList: MutableLiveData<List<Child>> = MutableLiveData()
    val childList: LiveData<List<Child>> get() = _childList

    init {
        getAllChild()
    }

    /**======== Database call Methods ========*/

    /**======== Add Child call Methods ========*/
    fun getAllChild() = viewModelScope.launch {
        getAllChildSafeCall()
    }

    private suspend fun getAllChildSafeCall() {
        withContext(Dispatchers.IO){
            try {
                val response = repository.loadChild()
                _childList.postValue(response)
            } catch (e: Exception) {
                _childList.postValue(emptyList())
            }
        }


    }
}