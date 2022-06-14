package com.example.dummyproject.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyproject.R
import com.example.dummyproject.base.BaseActivity
import com.example.dummyproject.databinding.MainActivityDataBinding
import com.example.dummyproject.ui.adapter.RepositoryAdapter
import com.example.dummyproject.ui.model.RepositoriesResponse
import com.example.dummyproject.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var binding: MainActivityDataBinding? = null
    private val mainViewModel: MainViewModel by viewModels()

    private var repositories: List<RepositoriesResponse.Item> = listOf()
    var lastItemClickedPosition: Int? = null

    private val repositoryAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter()
        { position ->

            if (lastItemClickedPosition != position) {
                lastItemClickedPosition?.let {
                    repositories[it].expand = false
                }
                lastItemClickedPosition = position
            }
            repositories[position].expand = !repositories[position].expand

            repositoryAdapter.datasetChanged(repositories)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.viewModel = mainViewModel

        /**======================== setting Adapter ========================*/
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


        binding?.errorView?.retryButton?.setOnClickListener {
            mainViewModel.getRepositories()
        }

        binding?.refreshLayout?.setOnRefreshListener {
            mainViewModel.getRepositories()
            binding?.refreshLayout?.isRefreshing = false
        }
//        loadDataFromCache()
        /**======================== OBSERVERS ========================*/

        /**======================== API RESPONSE OBSERVERS ========================*/
        mainViewModel.repositoriesResponse.observe(this) { response ->
            binding?.networkResult = response
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.items?.let {
                        repositories = it
                        repositoryAdapter.datasetChanged(repositories)
                    }
                }

                is NetworkResult.Error -> {
                    loadDataFromCache()
                }

            }


        }
    }


    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readRepositories.observe(this@MainActivity) { database ->
                if (database.isNotEmpty()) {
                    repositories = database[0].repositoriesResponse.items
                    repositoryAdapter.datasetChanged(repositories)
                }
            }
        }
    }

}