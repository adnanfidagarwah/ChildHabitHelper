package com.example.dummyproject.presentation.ui.home.ui.child_profile

import android.os.Bundle
import android.view.View
import com.example.dummyproject.R
import com.example.dummyproject.databinding.ChildProfileFragmentBinding
import com.example.dummyproject.presentation.base.BaseFragment
import com.example.dummyproject.presentation.util.extensions.viewBinding

class ChildProfileFragment : BaseFragment(R.layout.child_profile_fragment) {

    private val binding by viewBinding(ChildProfileFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

}