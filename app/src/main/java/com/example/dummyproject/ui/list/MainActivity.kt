package com.example.dummyproject.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyproject.R
import com.example.dummyproject.base.BaseActivity
import com.example.dummyproject.databinding.MainActivityDataBinding
import com.example.dummyproject.ui.details.MainActivity2
import com.example.dummyproject.ui.list.adapter.ProductAdapter
import com.example.dummyproject.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var binding: MainActivityDataBinding? = null
    private val mainViewModel: MainViewModel by viewModels()

    private var productAdapter: ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        productAdapter = ProductAdapter(ArrayList()) { id ->
            startActivity(Intent(this, MainActivity2::class.java).putExtra("id", id))
        }

        binding?.productsRecyclerview?.layoutManager = GridLayoutManager(
            this,
            2,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding?.productsRecyclerview?.adapter = productAdapter

        mainViewModel.productsResponse.observe(this, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { productAdapter?.dataSetChanged(it) }
                    shimmer()

                }
                is NetworkResult.Error -> {
                    shimmer()

                    binding?.shimmerViewContainer?.visibility = View.GONE
                    binding?.shimmerViewContainer?.stopShimmer()
                    binding?.productsRecyclerview?.visibility = View.VISIBLE
                }
                is NetworkResult.Loading -> {

                    shimmer(show = true)
                }
            }
        })
    }

    fun shimmer(show: Boolean = false) {
        if (show) {
            binding?.shimmerViewContainer?.visibility = View.VISIBLE
            binding?.shimmerViewContainer?.startShimmer()
            binding?.productsRecyclerview?.visibility = View.GONE
        } else {
            binding?.shimmerViewContainer?.visibility = View.GONE
            binding?.shimmerViewContainer?.stopShimmer()
            binding?.productsRecyclerview?.visibility = View.VISIBLE
        }

    }
}