package com.example.dummyproject.presentation.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment {
    constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    private fun baseActivity() = requireActivity() as BaseActivity

    // Loader Dialog

}