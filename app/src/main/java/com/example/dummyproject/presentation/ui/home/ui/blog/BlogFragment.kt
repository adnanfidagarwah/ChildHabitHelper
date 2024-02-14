package com.example.dummyproject.presentation.ui.home.ui.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dummyproject.R
import com.example.dummyproject.databinding.BlogFragmentBinding
import com.example.dummyproject.databinding.GoalsFragmentBinding
import com.example.dummyproject.presentation.base.BaseFragment
import com.example.dummyproject.presentation.util.Utils
import com.example.dummyproject.presentation.util.extensions.viewBinding

class BlogFragment : BaseFragment(R.layout.blog_fragment) {


    private val binding by viewBinding(BlogFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.headerLayout.rightImageview.visibility = View.VISIBLE
        binding.headerLayout.heading.text = "Blogs"

        binding.webview.loadUrl("https://parentalize.com/blogs/")

        binding.headerLayout.leftImageview.setOnClickListener {
            Utils. getHome(activity).drawHandling()
        }


    }
        companion object {

    }
}