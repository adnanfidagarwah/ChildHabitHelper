package com.example.dummyproject.presentation.ui.home.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyproject.R
import com.example.dummyproject.databinding.HomeFragmentBinding
import com.example.dummyproject.presentation.base.BaseFragment
import com.example.dummyproject.presentation.util.Utils
import com.example.dummyproject.presentation.util.extensions.viewBinding

class HomeFragment : BaseFragment(R.layout.home_fragment) {

    private val binding by viewBinding(HomeFragmentBinding::bind)

    private var goalsAdapter: GoalsAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Utils.getHome(activity).toggleBottomBar(true)

        binding.headerLayout.heading.text = "Hi!"

        binding.recyclerView.layoutManager =
            LinearLayoutManager(activity, GridLayoutManager.VERTICAL, false)
        goalsAdapter = GoalsAdapter()
        binding.recyclerView.adapter = goalsAdapter

        binding.imageView8.setOnClickListener {
            Utils.navigateTo(
                requireActivity(),
                HomeFragmentDirections.actionNavigationHomeToAddChildFragment(), binding.root
            )
        }
        binding.headerLayout.leftImageview.setOnClickListener {
           Utils.getHome(activity).drawHandling()
        }
    }
}