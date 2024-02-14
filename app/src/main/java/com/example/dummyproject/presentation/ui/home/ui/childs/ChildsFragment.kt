package com.example.dummyproject.presentation.ui.home.ui.childs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyproject.R
import com.example.dummyproject.databinding.ChildsFragmentBinding
import com.example.dummyproject.presentation.base.BaseFragment
import com.example.dummyproject.presentation.ui.home.ui.add_child.AddChildFragment
import com.example.dummyproject.presentation.util.Constants
import com.example.dummyproject.presentation.util.Utils
import com.example.dummyproject.presentation.util.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChildsFragment : BaseFragment(R.layout.childs_fragment) {

    private val binding by viewBinding(ChildsFragmentBinding::bind)
    private val viewModel: ChildsViewModel by viewModels()

    private var childAdapter: ChildAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.headerLayout.heading.text = "Profile Management"
        binding.buttonLayout.heading.text = "Add Another Child"

        binding.headerLayout.leftImageview.setOnClickListener {
            Utils.homeBackpressed(requireActivity(), binding.root)
        }

        binding.headerLayout.leftImageview.setOnClickListener {
            Utils.getHome(activity).drawHandling()
        }

        binding.recyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


        binding.buttonLayout.root.setOnClickListener {
            Utils.navigateTo(
                requireActivity(),
                ChildsFragmentDirections.actionNavigationaProfileManagementToAddChildFragment(),
                binding.root
            )
        }

        getDataFromAddChildFragment()


        /**======================== Database PARAMS OBSERVERS ========================*/

        viewModel.childList.observe(viewLifecycleOwner) { response ->
            childAdapter = ChildAdapter(response) { child ->
                val bundle = Bundle()
                bundle.putInt(AddChildFragment.CHILD_ID, child.uid)
                findNavController(view).navigate(R.id.addChildFragment, bundle)
            }
            binding.recyclerView.adapter = childAdapter
        }

    }

    private fun getDataFromAddChildFragment() {
        setFragmentResultListener(Constants.STATUS) { key, bundle ->
            // read from the bundle
           viewModel.getAllChild()
        }
    }

    companion object {

    }
}