package com.example.dummyproject

import com.example.dummyproject.base.BaseViewModel
import com.example.dummyproject.data.Repository
import com.example.dummyproject.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {


    fun getRecipes(queries: JSONObject) = coroutinesScope.launch {
        getRecipesSafeCall(queries)
    }


    private suspend fun getRecipesSafeCall(queries: JSONObject) {
//        _apiResponse.postValue(NetworkResult.Loading())
//        if (Utils.hasInternetConnection(activity = null)) {
            try {
                val response = repository.remote.getRecipes(queries)
//                _apiResponse.value = handleFoodRecipesResponse(response)

            } catch (e: Exception) {
//                 _apiResponse.postValue(NetworkResult.Error("Recipes not found."))
            }
       /* } else {
            apiResponse.value = NetworkResult.Error("No Internet Connection.")
        }*/
    }





}