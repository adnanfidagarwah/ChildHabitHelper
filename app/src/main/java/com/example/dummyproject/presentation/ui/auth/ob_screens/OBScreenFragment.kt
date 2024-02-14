package com.example.dummyproject.presentation.ui.auth.ob_screens

import android.R.attr.height
import android.R.attr.width
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.dummyproject.R
import com.example.dummyproject.databinding.OBScreenFragmentBinding
import com.example.dummyproject.presentation.base.BaseFragment
import com.example.dummyproject.presentation.ui.home.HomeActivity
import com.example.dummyproject.presentation.util.Utils
import com.example.dummyproject.presentation.util.animations.ProgressBarAnimation
import com.example.dummyproject.presentation.util.extensions.viewBinding


class OBScreenFragment : BaseFragment(R.layout.o_b_screen_fragment) {

    private val binding by viewBinding(OBScreenFragmentBinding::bind)
    var obScreenPagerAdapter: OBScreenPagerAdapter? = null
    var obScreenModels: ArrayList<OBScreenModel>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**====================Setting Data in Model Class of OBScreens ========================*/
        obScreenModels = ArrayList()

        obScreenModels?.add(
            OBScreenModel(
                getString(R.string.ob_screen_one_description),
                R.drawable.illustration_one
            )
        )

        obScreenModels?.add(
            OBScreenModel(
                getString(R.string.ob_screen_two_description),
                R.drawable.illustration_two
            )
        )

        obScreenModels?.add(
            OBScreenModel(
                getString(R.string.ob_screen_three_description),
                R.drawable.illustration_three
            )
        )

        obScreenPagerAdapter = OBScreenPagerAdapter(activity, obScreenModels)
        binding.viewPager.adapter = obScreenPagerAdapter


        /**======================== CLICK LISTENERS ========================*/

        binding.imageButton3.setOnClickListener {
            startActivity(Intent(requireActivity(), HomeActivity::class.java))
            requireActivity().finish()
        }

        /**======================== PagerListener ========================*/
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                tabSelection(position)
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun tabSelection(position: Int) {

        val height = (5 * requireContext().resources.displayMetrics.density + 0.5f).toInt()
        val width = (25 * requireContext().resources.displayMetrics.density + 0.5f).toInt()


        binding.tab1.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.circle_default_tab)
        binding.tab2.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.circle_default_tab)
        binding.tab3.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.circle_default_tab)

        binding.tab1.layoutParams.width = height
        binding.tab1.layoutParams.height = height
        binding.tab1.requestLayout()

        binding.tab2.layoutParams.width = height
        binding.tab2.layoutParams.height = height
        binding.tab2.requestLayout()

        binding.tab3.layoutParams.width = height
        binding.tab3.layoutParams.height = height
        binding.tab3.requestLayout()

        when (position) {
            0 -> {
                binding.tab1.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.circle_selected_tab)
                binding.tab1.layoutParams.width = width
                binding.tab1.layoutParams.height = height
                binding.tab1.requestLayout()
                setLinearProgressIndicatorValue(33)
            }

            1 -> {
                binding.tab2.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.circle_selected_tab)
                binding.tab2.layoutParams.width = width
                binding.tab2.layoutParams.height = height
                binding.tab2.requestLayout()

                setLinearProgressIndicatorValue(66)
            }

            2 -> {
                binding.tab3.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.circle_selected_tab)
                binding.tab3.layoutParams.width = width
                binding.tab3.layoutParams.height = height
                binding.tab3.requestLayout()

                setLinearProgressIndicatorValue(100)
            }
        }
    }

    private fun setLinearProgressIndicatorValue(endValue: Int) {

        val anim = ProgressBarAnimation(
            progressBar = binding.progressBar,
            from = binding.progressBar.progress.toFloat(),
            to = endValue.toFloat(),
        )
        anim.duration = 300
        binding.progressBar.startAnimation(anim)
    }


    companion object {

        @JvmStatic
        fun newInstance() = OBScreenFragment()
    }
}