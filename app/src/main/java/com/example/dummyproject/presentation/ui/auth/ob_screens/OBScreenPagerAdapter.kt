package com.example.dummyproject.presentation.ui.auth.ob_screens

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.example.dummyproject.R
import com.example.dummyproject.databinding.OBScreenItemDataBinding

import java.util.*

class OBScreenPagerAdapter(
    private var context: Context?,
    private var obScreenModels: ArrayList<OBScreenModel>?
) : PagerAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private var binding: OBScreenItemDataBinding? = null

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        layoutInflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding =
            DataBindingUtil.inflate(layoutInflater!!, R.layout.ob_screen_item, container, false)
        binding?.obScreenModel = obScreenModels!![position]
        container.addView(binding?.root)
        return binding?.root!!
    }

    override fun getCount(): Int {
        return obScreenModels!!.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, objects: Any) {
        val view = objects as View
        container.removeView(view)
    }
}