package com.example.dummyproject.presentation.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.dummyproject.R
import com.example.dummyproject.databinding.OBScreenFragmentBinding
import com.example.dummyproject.databinding.SignupFragmentBinding
import com.example.dummyproject.presentation.base.BaseFragment
import com.example.dummyproject.presentation.ui.auth.AuthActivity
import com.example.dummyproject.presentation.ui.auth.ob_screens.OBScreenFragmentDirections
import com.example.dummyproject.presentation.ui.home.HomeActivity
import com.example.dummyproject.presentation.util.Utils
import com.example.dummyproject.presentation.util.extensions.viewBinding


class SignupFragment : BaseFragment(R.layout.signup_fragment) {
    private val binding by viewBinding(SignupFragmentBinding::bind)
    companion object {
        @JvmStatic
        fun newInstance() = SignupFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupButton.setOnClickListener {
            startActivity(Intent(requireActivity(), HomeActivity::class.java))
            requireActivity().finish()
        }
    }

}