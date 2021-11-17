package com.example.dummyproject.util

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import android.widget.RatingBar




class DataBindingAdapters {
    companion object {
        @BindingAdapter("profileImage")
        @JvmStatic
        fun setImageUrl(view: ImageView, imageUrl: String?) {
            if (imageUrl == null) {
                view.setImageURI(null)
            } else {
                view.load(imageUrl) {
                    crossfade(true)
//                    placeholder(R.drawable.app_logo)
                }
            }
        }

        @BindingAdapter("rating")
        @JvmStatic
        fun setRating(view: RatingBar?, rating: Double) {
            if (view != null) {
                val rate = rating.toFloat()
                view.rating = rate
            }
        }


    }

}