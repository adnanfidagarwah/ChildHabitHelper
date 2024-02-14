package com.example.dummyproject.presentation.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dummyproject.R
import com.example.dummyproject.databinding.HomeActivityBinding
import com.example.dummyproject.presentation.ui.home.ui.faqs.aboutUs
import com.example.dummyproject.presentation.ui.home.ui.faqs.faqs
import com.example.dummyproject.presentation.ui.home.ui.faqs.privacyPolicy
import com.example.dummyproject.presentation.util.Interpolators
import com.example.dummyproject.presentation.util.Utils
import com.example.dummyproject.presentation.util.extensions.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val binding by viewBinding(HomeActivityBinding::inflate)

    private var currentAnimator: ViewPropertyAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        Utils.changeStatusBarColor(R.color.slightly_gray, this, false)

        val navView: BottomNavigationView = binding.bottomNavView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)

        navView.setupWithNavController(navController)


        binding.backgroundImage.setOnClickListener {
            Utils.changeStatusBarColor(R.color.slightly_gray, this, false)
            binding.backgroundImage.visibility = View.GONE
            binding.introChildIcon.visibility = View.GONE
            binding.imageView9.visibility = View.GONE
            binding.introBox.visibility = View.GONE
        }

        setDrawerData()

        binding.drawer.addChildLayout.root.setOnClickListener {
            findNavController(R.id.nav_host_fragment_activity_home).navigate(R.id.addChildFragment)
            drawHandling()
        }

        binding.drawer.profileManagementLayout.root.setOnClickListener {
            findNavController(R.id.nav_host_fragment_activity_home).navigate(R.id.navigation_profile_management)
            drawHandling()
        }



        binding.drawer.faqsLayout.root.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("title", "FAQs")
            bundle.putParcelableArrayList("data", ArrayList(faqs))

            findNavController(R.id.nav_host_fragment_activity_home).navigate(
                R.id.FAQsFragment,
                bundle
            )
            drawHandling()
        }

        binding.drawer.aboutUsLayout.root.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("title", "About Us")
            bundle.putParcelableArrayList("data", ArrayList(aboutUs))

            findNavController(R.id.nav_host_fragment_activity_home).navigate(
                R.id.FAQsFragment,
                bundle
            )
            drawHandling()
        }

        binding.drawer.privacyPolicyLayout.root.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("title", "Privacy Policy")
            bundle.putParcelableArrayList("data", ArrayList(privacyPolicy))

            findNavController(R.id.nav_host_fragment_activity_home).navigate(
                R.id.FAQsFragment,
                bundle
            )
            drawHandling()
        }

        binding.drawer.blogLayout.root.setOnClickListener {
            findNavController(R.id.nav_host_fragment_activity_home).navigate(R.id.blogFragment)
            drawHandling()
        }



        onBackPressedDispatcher.addCallback(
            this /* lifecycle owner */,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Back is pressed... Finishing the activity
                    if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    } else {
                        Utils.homeBackpressed(this@HomeActivity, binding.root)
                    }

                }
            })
    }

    private fun setDrawerData() {
        binding.drawer.addChildLayout.heading.text = getString(R.string.add_child)
        binding.drawer.addChildLayout.icon.setBackgroundResource(R.drawable.ic_add_person)

        binding.drawer.profileManagementLayout.heading.text = getString(R.string.profile_management)
        binding.drawer.profileManagementLayout.icon.setBackgroundResource(R.drawable.ic_person)

        binding.drawer.faqsLayout.heading.text = getString(R.string.faqs)
        binding.drawer.faqsLayout.icon.setBackgroundResource(R.drawable.ic_faqs)

        binding.drawer.blogLayout.heading.text = getString(R.string.blog)
        binding.drawer.blogLayout.icon.setBackgroundResource(R.drawable.ic_blogs)

        binding.drawer.aboutUsLayout.heading.text = getString(R.string.about_us)
        binding.drawer.aboutUsLayout.icon.setBackgroundResource(R.drawable.ic_about_us)

        binding.drawer.privacyPolicyLayout.heading.text = getString(R.string.privacy_policy)
        binding.drawer.privacyPolicyLayout.icon.setBackgroundResource(R.drawable.ic_privacy_policy)
    }

    fun drawHandling() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        setDrawerData()
    }

    fun bottomNavigationClick(id: Int) {
        val view: View = binding.bottomNavView.findViewById(id)
        view.performClick()
    }

    fun toggleBottomBar(show: Boolean) {
        binding.bottomNavView.isEnabled = show

        binding.bottomNavView.let { view ->
            currentAnimator?.cancel()
            view.clearAnimation()

            val translationY = when (show) {
                true -> 0f
                false -> view.height.toFloat()
            }
            val duration = when (show) {
                true -> 225L
                false -> 175L
            }
            val interpolator = when (show) {
                true -> Interpolators.linearOutSlowInInterpolator
                false -> Interpolators.fastOutLinearInInterpolator
            }

            if (view.translationY == translationY) {
                return
            }

            if (view.isLaidOut && !view.isLayoutRequested) {
                currentAnimator = view
                    .animate()
                    .translationY(translationY)
                    .setDuration(duration)
                    .setInterpolator(interpolator)
                    .setListener(object : AnimatorListenerAdapter() {
                        var isCanceled = false

                        override fun onAnimationStart(animation: Animator) {
                            view.isVisible = true
                        }

                        override fun onAnimationEnd(animation: Animator) {
                            currentAnimator = null

                            if (!isCanceled) {
                                view.isInvisible = !show
                            }
                        }

                        override fun onAnimationCancel(animation: Animator) {
                            isCanceled = true
                        }
                    })
            } else {
                view.translationY = translationY
                view.isInvisible = !show
            }
        }
    }
}