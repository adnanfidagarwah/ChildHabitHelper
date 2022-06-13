package com.example.dummyproject.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.example.dummyproject.R
import net.cachapa.expandablelayout.ExpandableLayout


@BindingAdapter("hide")
fun hideView(view: View?, show: Boolean = false) {
    if (show) {
        view?.visibility = View.VISIBLE
    } else {
        view?.visibility = View.GONE
    }
}


@BindingAdapter("expand")
fun expandView(expandableLayout: ExpandableLayout?, hide: Boolean = false) {
    if (hide) {
        expandableLayout?.expand(true)
    } else {
        expandableLayout?.collapse(true)
    }
}



@BindingAdapter("profileImage")
fun setImageUrl(view: ImageView, imageUrl: String?) {
    if (imageUrl == null) {
        view.setImageURI(null)
    } else {
        view.load(imageUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
            placeholder(R.drawable.ic_launcher_background)
        }
    }
}