package com.example.dummyproject.presentation.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.dummyproject.R
import com.example.dummyproject.databinding.AuthActivityBinding
import com.example.dummyproject.presentation.util.extensions.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class AuthActivity : AppCompatActivity() {

    private val binding by viewBinding(AuthActivityBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}