package com.example.dummyproject.presentation.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.willy.ratingbar.ScaleRatingBar

class DataBindingAdapters {
    companion object {
        /* @BindingAdapter("android:src")
         @JvmStatic
         fun setImageUrl(view: ImageView, imageUrl: String?) {
             if (imageUrl == null) {
                 view.setImageURI(null)
             } else {
                 view.load(imageUrl) {
                     crossfade(true)
                     placeholder(R.drawable.app_logo)
                     transformations(CircleCropTransformation())
                 }
             }
         }

         @BindingAdapter("android:src")
         @JvmStatic
         fun setImageUri(view: ImageView, imageUri: Uri?) {
             view.setImageURI(imageUri)
         }

         @BindingAdapter("android:src")
         @JvmStatic
         fun setImageDrawable(view: ImageView, drawable: Drawable?) {
             view.setImageDrawable(drawable)
         }*/

        @BindingAdapter("android:src")
        @JvmStatic
        fun setImageResource(imageView: ImageView, resource: Int) {
            imageView.setImageResource(resource)
        }

        @BindingAdapter("ratingValue")
        fun setRating(scaleRatingBar: ScaleRatingBar, rating: Float) {
            scaleRatingBar.rating = rating
        }
    }

}