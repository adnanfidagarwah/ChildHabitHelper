package com.example.dummyproject.ui.details

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import coil.load
import com.example.dummyproject.R
import com.example.dummyproject.base.BaseActivity
import com.example.dummyproject.databinding.MainActivity2DataBinding
import com.example.dummyproject.util.Loader
import com.example.dummyproject.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity2 : BaseActivity() {

    private var binding: MainActivity2DataBinding? = null
    private val mainViewModel: Main2ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
//        setContentView(R.layout.activity_main2)

        mainViewModel.getProduct(intent.extras?.get("id").toString())

        mainViewModel.productResponse.observe(this, { response ->
            when (response) {
                is NetworkResult.Success -> {
                     binding?.productNameTextview?.text = response.data?.title
                     binding?.productPriceTextview?.text = response.data?.price.toString()
                     binding?.descriptionTextview?.text = response.data?.description
                    binding?.imageView?.load(response.data?.image)
                    binding?.ratingBar?.rating = response.data?.rating?.rate?.toFloat()!!
                    binding?.main?.visibility = View.VISIBLE

                    Loader.hideLoader()
                }
                is NetworkResult.Error -> {
                    Loader.hideLoader()
                }
                is NetworkResult.Loading -> {
                    Loader.showLoader(this)
                }
            }
        })
    }
}