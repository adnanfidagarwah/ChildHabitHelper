package com.example.dummyproject.presentation.ui.home.ui.add_child

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
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
class AddChildViewModel @Inject constructor(val repository: Repository, savedStateHandle: SavedStateHandle) :
    BaseViewModel() {

    /**======== LiveData Objects ========*/
    var childNameMutableLiveData: MutableLiveData<String> = MutableLiveData()


    /**======== Edit child Params MutableLiveData ========*/
    private var  _childEditPost: MutableLiveData<ClickEvent<Child>> = MutableLiveData()
    val childEditPost: LiveData<ClickEvent<Child>> get() = _childEditPost

    /**======== add child Params MutableLiveData ========*/
    private var  _childPost: MutableLiveData<ClickEvent<Child>> = MutableLiveData()
    val childPost: LiveData<ClickEvent<Child>> get() = _childPost

    /**======== get Add Child Success Response MutableLiveData ========*/
    private var  _childReponse: MutableLiveData<ClickEvent<String>> = MutableLiveData()
    val childReponse: LiveData<ClickEvent<String>> get() = _childReponse


    fun clickOnAddChild(bitmap: Bitmap) {
        if (childNameMutableLiveData.value == null)
            _childPost.postValue(ClickEvent(Child(name = "", profilePhoto = bitmap)))
        else
            _childPost.postValue(ClickEvent(Child(name = childNameMutableLiveData.value!!, profilePhoto = bitmap)))
    }

    init {
        if(savedStateHandle.get<Int>(AddChildFragment.CHILD_ID) != null)
            getChildById(savedStateHandle.get<Int>(AddChildFragment.CHILD_ID)!!)
    }
    /**======== Database call Methods ========*/

    /**======== Get Child By Id call Methods ========*/
    private fun getChildById(uid: Int) = viewModelScope.launch {
        getChildByIdSafeCall(uid)
    }

    private suspend fun getChildByIdSafeCall(uid: Int) {
        withContext(Dispatchers.IO){
            try {
                val response = repository.getChildById(uid)
                _childEditPost.postValue(ClickEvent(response))
                childNameMutableLiveData.postValue(response.name.orEmpty())
            } catch (e: Exception) {

            }
        }


    }



    /**======== Add Child call Methods ========*/
    fun getAddChild(child: Child) = viewModelScope.launch {
        getAddChildSafeCall(child)
    }

    private suspend fun getAddChildSafeCall(child: Child) {
       withContext(Dispatchers.IO){
           try {
               val response = repository.insertChild(child)
               _childReponse.postValue(ClickEvent(repository.loadChild().size.toString()))
           } catch (e: Exception) {
               _childReponse.postValue(ClickEvent("false"))
           }
       }


    }


    /**======== Update Child call Methods ========*/
     fun getUpdateChild(child: Child) = viewModelScope.launch {
        getUpdateChildSafeCall(child)
    }

    private suspend fun getUpdateChildSafeCall(child: Child) {
        withContext(Dispatchers.IO){
            try {
                val response = repository.updateChild(child)
                _childReponse.postValue(ClickEvent(repository.loadChild().size.toString()))
            } catch (e: Exception) {
                _childReponse.postValue(ClickEvent("false"))
            }
        }


    }
}