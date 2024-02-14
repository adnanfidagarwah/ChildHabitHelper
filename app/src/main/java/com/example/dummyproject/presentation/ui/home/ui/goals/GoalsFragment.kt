package com.example.dummyproject.presentation.ui.home.ui.goals

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyproject.R
import com.example.dummyproject.data.local.database.goal.Goal
import com.example.dummyproject.databinding.GoalsFragmentBinding
import com.example.dummyproject.presentation.base.BaseFragment
import com.example.dummyproject.presentation.util.Utils
import com.example.dummyproject.presentation.util.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoalsFragment : BaseFragment(R.layout.goals_fragment) {

    private val binding by viewBinding(GoalsFragmentBinding::bind)
    private val viewModel: GoalsViewModel by viewModels()

    private var setGoalsAdapter: SetGoalsAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.headerLayout.rightImageview.visibility = View.VISIBLE
        binding.headerLayout.heading.text = "Set Goals for Tony"


        binding.headerLayout.leftImageview.setOnClickListener {
            Utils.getHome(activity).drawHandling()
        }

        binding.buttonLayout.root.setOnClickListener {
            Utils.navigateTo(
                requireActivity(),
                GoalsFragmentDirections.actionNavigationGoalsToSetGoalFragment(), binding.root
            )
        }

        /**======================== Database PARAMS OBSERVERS ========================*/

        viewModel.goalCategoryList.observe(viewLifecycleOwner) { response ->
            if (response.size >= 4) {
                binding.routinesHeading.text = response[0].name
                binding.activitiesHeading.text = response[1].name
                binding.healthHeading.text = response[2].name
                binding.othersHeading.text = response[3].name
            }
        }

        viewModel.goalList.observe(viewLifecycleOwner) { response ->
            if (response.isNotEmpty()) {

                val routines  = response.filter { it.goalCategoryId == 1  }
                setGoalsAdapter = SetGoalsAdapter(routines)

                binding.routinesGoals.layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                binding.routinesGoals.adapter = setGoalsAdapter


                val activities  = response.filter { it.goalCategoryId == 2  }
                setGoalsAdapter = SetGoalsAdapter(activities)

                binding.activitiesGoals.layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                binding.activitiesGoals.adapter = setGoalsAdapter


                val health  = response.filter { it.goalCategoryId == 3  }
                setGoalsAdapter = SetGoalsAdapter(health)

                binding.healthGoals.layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                binding.healthGoals.adapter = setGoalsAdapter


                val others  = response.filter { it.goalCategoryId == 4  }
                setGoalsAdapter = SetGoalsAdapter(others)

                binding.othersGoals.layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                binding.othersGoals.adapter = setGoalsAdapter
            }
        }





        binding.buttonLayout.heading.text = "Save Goal"


    }
}