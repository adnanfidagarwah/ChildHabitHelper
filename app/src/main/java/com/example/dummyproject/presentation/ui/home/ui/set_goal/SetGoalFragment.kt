package com.example.dummyproject.presentation.ui.home.ui.set_goal

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dummyproject.R
import com.example.dummyproject.databinding.SetGoalFragmentBinding
import com.example.dummyproject.presentation.base.BaseFragment
import com.example.dummyproject.presentation.util.Utils
import com.example.dummyproject.presentation.util.extensions.viewBinding
import com.example.dummyproject.presentation.util.observer.gone
import com.example.dummyproject.presentation.util.observer.visible

class SetGoalFragment : BaseFragment(R.layout.set_goal_fragment) {

    private val binding by viewBinding(SetGoalFragmentBinding::bind)

    private var imagePickerAdapter: ImagePickerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.headerLayout.rightImageview.visibility = View.VISIBLE
        binding.headerLayout.heading.text = "Set Goals for Tony"

        binding.buttonLayout.heading.text = "Save Goal"

        binding.headerLayout.leftImageview.setOnClickListener {
            Utils.homeBackpressed(requireActivity(), binding.root)
        }


        binding.include2.heading.setOnClickListener{
            binding.include2.heading.isCursorVisible = true
            binding.include2.heading.isFocusableInTouchMode = true
            binding.include2.heading.inputType = InputType.TYPE_CLASS_TEXT
            binding.include2.heading.requestFocus()
        }

        binding.include2.heading.setOnClickListener{
            binding.include2.heading.isCursorVisible = true
            binding.include2.heading.isFocusableInTouchMode = true
            binding.include2.heading.inputType = InputType.TYPE_CLASS_TEXT
            binding.include2.heading.requestFocus()
        }

        binding.include2.heading.setOnClickListener{
            binding.include2.heading.isCursorVisible = true
            binding.include2.heading.isFocusableInTouchMode = true
            binding.include2.heading.inputType = InputType.TYPE_CLASS_TEXT
            binding.include2.heading.requestFocus()
        }


        imagePickerAdapter = ImagePickerAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 4)
        binding.recyclerView.adapter = imagePickerAdapter


        binding.backgroundImage.setOnClickListener {

            if (binding.arrowOne.isVisible && binding.introOne.isVisible) {
                binding.arrowOne.gone()
                binding.introOne.gone()

                binding.arrowTwo.visible()
                binding.introTwo.visible()

            } else if (binding.arrowTwo.isVisible && binding.introTwo.isVisible) {

                binding.arrowTwo.gone()
                binding.introTwo.gone()

                binding.arrowThree.visible()
                binding.introThree.visible()
                binding.pointDeductingTextview.visible()
                binding.pointRewardingTextview.visible()

            } else if (binding.arrowThree.isVisible && binding.arrowThree.isVisible) {
                binding.arrowThree.gone()
                binding.introThree.gone()

                binding.pointDeductingTextview.gone()
                binding.pointRewardingTextview.gone()
            }
        }

        binding.include2.goalIcon.setOnClickListener {
            if(binding.recyclerView.isVisible)
                binding.recyclerView.gone()
            else
                binding.recyclerView.visible()
        }
    }


    companion object
}