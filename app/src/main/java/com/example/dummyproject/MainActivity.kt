package com.example.dummyproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.dummyproject.databinding.MainActivityDataBinding
import com.example.dummyproject.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: MainActivityDataBinding? = null
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        mainViewModel.getRecipes(JSONObject())

//        mainViewModel.apiResponse.observe(this, { response ->
//            when (response) {
//                is NetworkResult.Success -> {
//                    Toast.makeText(
//                        this@MainActivity,
//                        response.message.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
////                    response.data?.let { mAdapter.setData(it) }
//                }
//                is NetworkResult.Error -> {
//                    Toast.makeText(
//                        this@MainActivity,
//                        response.message.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                is NetworkResult.Loading -> {
//                    Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
    }
}