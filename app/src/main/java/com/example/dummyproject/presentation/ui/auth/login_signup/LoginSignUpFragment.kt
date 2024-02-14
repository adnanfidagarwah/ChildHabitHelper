package com.example.dummyproject.presentation.ui.auth.login_signup

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dummyproject.R
import com.example.dummyproject.databinding.LoginSignUpFragmentBinding
import com.example.dummyproject.presentation.base.BaseFragment
import com.example.dummyproject.presentation.ui.auth.login.LoginFragment
import com.example.dummyproject.presentation.ui.auth.ob_screens.OBScreenFragment
import com.example.dummyproject.presentation.ui.auth.signup.SignupFragment
import com.example.dummyproject.presentation.util.extensions.viewBinding
import com.google.android.material.tabs.TabLayoutMediator

class LoginSignUpFragment : BaseFragment(R.layout.login_sign_up_fragment) {

    private val binding by viewBinding(LoginSignUpFragmentBinding::bind)
    private var adapter: TabsPagerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TabsPagerAdapter()
        binding.viewPager.adapter = adapter

        val tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = adapter?.getTabTitle(position)
            }

        tabLayoutMediator.attach()

    }

    override fun onDestroyView() {
        adapter = null
        super.onDestroyView()
    }

    private inner class TabsPagerAdapter : FragmentStateAdapter(this) {

        private val tabs = LoginSignUpTabs.values()

        override fun getItemCount() = tabs.size

        override fun createFragment(position: Int): Fragment {
            return when (tabs[position]) {
                LoginSignUpTabs.Login -> LoginFragment.newInstance()
                LoginSignUpTabs.SignUp -> SignupFragment.newInstance()
            }
        }

        fun getTabTitle(position: Int): String? {
            return if (position < tabs.size) {
                getString(tabs[position].titleId)
            } else {
                null
            }
        }

    }
}

enum class LoginSignUpTabs(@StringRes val titleId: Int) {
    Login(R.string.login),
    SignUp(R.string.signup)
}