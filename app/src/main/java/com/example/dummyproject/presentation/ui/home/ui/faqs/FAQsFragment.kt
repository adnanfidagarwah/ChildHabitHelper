package com.example.dummyproject.presentation.ui.home.ui.faqs

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyproject.R
import com.example.dummyproject.databinding.FaqsFragmentBinding
import com.example.dummyproject.presentation.base.BaseFragment
import com.example.dummyproject.presentation.util.Utils
import com.example.dummyproject.presentation.util.extensions.viewBinding

class FAQsFragment : BaseFragment(R.layout.faqs_fragment) {

    private val binding by viewBinding(FaqsFragmentBinding::bind)

    private var faQsAdapter: FAQsAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Utils.changeStatusBarColor(R.color.slightly_gray, requireActivity(), false)
        binding.headerLayout.heading.text = arguments?.getString("title")

        binding.recyclerView.layoutManager =
            LinearLayoutManager(activity, GridLayoutManager.VERTICAL, false)
        faQsAdapter = FAQsAdapter(ArrayList(arguments?.getParcelableArrayList("data")))
        binding.recyclerView.adapter = faQsAdapter

        binding.headerLayout.leftImageview.setOnClickListener {
            Utils. getHome(activity).drawHandling()
        }
    }

    companion object {

    }
}