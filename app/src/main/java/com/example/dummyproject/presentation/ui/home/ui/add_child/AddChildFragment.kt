package com.example.dummyproject.presentation.ui.home.ui.add_child

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dummyproject.R
import com.example.dummyproject.data.local.database.child.Child
import com.example.dummyproject.databinding.AddChildFragmentBinding
import com.example.dummyproject.presentation.base.BaseFragment
import com.example.dummyproject.presentation.util.Constants
import com.example.dummyproject.presentation.util.Utils
import com.example.dummyproject.presentation.util.extensions.viewBinding
import com.example.dummyproject.presentation.util.image_picker.DialogImageAttachment
import com.example.dummyproject.presentation.util.image_picker.ImageAttachentCallback
import com.example.dummyproject.presentation.util.observer.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import id.zelory.compressor.Compressor
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class AddChildFragment : BaseFragment(R.layout.add_child_fragment) {

    private val binding by viewBinding(AddChildFragmentBinding::bind)
    private val viewModel: AddChildViewModel by viewModels()

    private var dialogImageAttachment: DialogImageAttachment? = null
    private var userProfile: Bitmap? = null
    private var childId: Int = 0

    companion object {
        const val CHILD_ID = "CHILD_ID"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Utils.changeStatusBarColor(R.color.slightly_gray, requireActivity(), false)

        binding.viewModel = viewModel

        binding.headerLayout.heading.text = "Add Child Details"
        binding.button.heading.text = "Proceed"

        binding.headerLayout.leftImageview.setOnClickListener {
            Utils.homeBackpressed(requireActivity(), binding.root)
        }

        binding.button.root.setOnClickListener {
            if (userProfile == null)
                viewModel.clickOnAddChild(binding.childPic.drawable.toBitmap())
            else
                viewModel.clickOnAddChild(userProfile!!)
        }

        binding.childPic.setOnClickListener {
            selectImage()
        }


        /**======================== Database PARAMS OBSERVERS ========================*/

        viewModel.childEditPost.observe(viewLifecycleOwner, EventObserver {
            binding.childPic.setImageResource(0)
            binding.childPic.setImageBitmap(it.profilePhoto)
            userProfile = it.profilePhoto
            binding.editText.setText(it.name)
            childId = it.uid
        })


        viewModel.childPost.observe(viewLifecycleOwner, EventObserver {
            if (it.name == null) {
                binding.editText.error = "Error"
            } else {
                if (viewModel.childEditPost.value == null)
                    viewModel.getAddChild(it)
                else
                    viewModel.getUpdateChild(
                        Child(
                            uid = childId,
                            name = it.name,
                            profilePhoto = it.profilePhoto
                        )
                    )
            }
        })

        viewModel.childReponse.observe(viewLifecycleOwner, EventObserver { response ->
            Toast.makeText(requireActivity(), "Child Added Successfully", Toast.LENGTH_SHORT).show()
            setFragmentResult(
                Constants.STATUS,
                bundleOf(
                    Constants.STATUS to true
                )
            )
            findNavController().navigateUp()
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
        dialogImageAttachment?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        dialogImageAttachment?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun selectImage() {
        dialogImageAttachment =
            DialogImageAttachment.make(requireActivity(), this@AddChildFragment)
                .setType(DialogImageAttachment.Profile)
                .setCallback(object : ImageAttachentCallback {

                    override fun onSuccess(file: File?) {
                        if (file != null) {
                            lifecycleScope.launch {
                                val compressedFile = Compressor.compress(requireContext(), file)
                                userProfile = BitmapFactory.decodeFile(compressedFile.absolutePath)
                                binding.childPic.setImageResource(0)
                                binding.childPic.setImageBitmap(userProfile)

                            }


                        }

                    }

                    override fun onFailure(error: String?) {
                        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
                    }

                    override fun onClick(intent: Intent?, id: Int) {
                        activity?.startActivityFromFragment(this@AddChildFragment, intent, id)
                    }
                })
        dialogImageAttachment?.show()
    }
}