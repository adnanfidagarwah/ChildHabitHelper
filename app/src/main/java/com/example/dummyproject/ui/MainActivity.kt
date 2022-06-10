package com.example.dummyproject.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyproject.R
import com.example.dummyproject.base.BaseActivity
import com.example.dummyproject.databinding.MainActivityDataBinding
import com.example.dummyproject.ui.adapter.RepositoryAdapter
import com.example.dummyproject.util.Loader
import com.example.dummyproject.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var binding: MainActivityDataBinding? = null
    private val mainViewModel: MainViewModel by viewModels()

    private val repositoryAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter()
        { position ->

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.recyclerView?.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        val dividerItemDecoration = DividerItemDecoration(
            this,
            LinearLayoutManager.VERTICAL
        )
        binding?.recyclerView?.addItemDecoration(dividerItemDecoration)

        binding?.recyclerView?.adapter = repositoryAdapter


        mainViewModel.repositoriesResponse.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    Loader.hideLoader()
                }
                is NetworkResult.Error -> {
                    Loader.hideLoader()
                }
                is NetworkResult.Loading -> {

                    Loader.showLoader(this)
                }
            }
        }
    }


}