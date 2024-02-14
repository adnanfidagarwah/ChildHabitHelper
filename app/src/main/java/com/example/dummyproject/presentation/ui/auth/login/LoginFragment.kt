package com.example.dummyproject.presentation.ui.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dummyproject.R
import com.example.dummyproject.databinding.LoginFragmentBinding
import com.example.dummyproject.databinding.SignupFragmentBinding
import com.example.dummyproject.presentation.base.BaseFragment
import com.example.dummyproject.presentation.ui.auth.ob_screens.OBScreenFragment
import com.example.dummyproject.presentation.ui.home.HomeActivity
import com.example.dummyproject.presentation.util.extensions.viewBinding


class LoginFragment : BaseFragment(R.layout.login_fragment) {
    private val binding by viewBinding(LoginFragmentBinding::bind)
    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            startActivity(Intent(requireActivity(), HomeActivity::class.java))
            requireActivity().finish()
        }
    }
}