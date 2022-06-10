package com.example.dummyproject.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.example.dummyproject.R


@BindingAdapter("hide")
fun hideView(view: View?, hide: Boolean = false) {
    if (hide) {
        view?.visibility = View.GONE
    } else {
        view?.visibility = View.VISIBLE
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