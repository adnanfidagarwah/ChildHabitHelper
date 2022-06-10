package com.example.dummyproject.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.dummyproject.R
import com.example.dummyproject.base.BaseActivity
import com.example.dummyproject.databinding.MainActivityDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var binding: MainActivityDataBinding? = null
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


//        mainViewModel.productsResponse.observe(this) { response ->
//            when (response) {
//                is NetworkResult.Success -> {
//                    response.data?.let { productAdapter?.dataSetChanged(it) }
//                    shimmer()
//
//                }
//                is NetworkResult.Error -> {
//                    shimmer()
//
//                    binding?.shimmerViewContainer?.visibility = View.GONE
//                    binding?.shimmerViewContainer?.stopShimmer()
//                    binding?.productsRecyclerview?.visibility = View.VISIBLE
//                }
//                is NetworkResult.Loading -> {
//
//                    shimmer(show = true)
//                }
//            }
//        }
//    }

    }
}