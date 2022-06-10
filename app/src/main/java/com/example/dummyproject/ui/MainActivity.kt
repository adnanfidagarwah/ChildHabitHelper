package com.example.dummyproject.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyproject.R
import com.example.dummyproject.base.BaseActivity
import com.example.dummyproject.databinding.MainActivityDataBinding
import com.example.dummyproject.ui.adapter.RepositoryAdapter
import com.example.dummyproject.ui.model.RepositoriesResponse
import com.example.dummyproject.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var binding: MainActivityDataBinding? = null
    private val mainViewModel: MainViewModel by viewModels()

    private var repositories: ArrayList<RepositoriesResponse.Item> = ArrayList()

    private val repositoryAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter()
        { position ->
            repositories[position].expand = !repositories[position].expand
            repositoryAdapter.datasetChanged(repositories)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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
        /**======================== OBSERVERS ========================*/

        /**======================== API RESPONSE OBSERVERS ========================*/
        mainViewModel.repositoriesResponse.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.items?.let {
                        repositories = it
                        repositoryAdapter.datasetChanged(repositories)
                    }

                    binding?.shimmerView?.root?.visibility = View.GONE
                    binding?.recyclerView?.visibility = View.VISIBLE
                    binding?.shimmerView?.shimmerLayout?.stopShimmer()
                    binding?.errorView?.root?.visibility = View.GONE
                }
                is NetworkResult.Error -> {
                    binding?.shimmerView?.root?.visibility = View.GONE
                    binding?.recyclerView?.visibility = View.GONE
                    binding?.shimmerView?.shimmerLayout?.stopShimmer()
                    binding?.errorView?.root?.visibility = View.VISIBLE

                }
                is NetworkResult.Loading -> {
                    binding?.shimmerView?.root?.visibility = View.VISIBLE
                    binding?.recyclerView?.visibility = View.GONE
                    binding?.shimmerView?.shimmerLayout?.startShimmer()
                    binding?.errorView?.root?.visibility = View.GONE
                }
            }
        }
    }


}