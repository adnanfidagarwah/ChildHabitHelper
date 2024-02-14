package com.example.dummyproject.presentation.ui.home.ui.set_goal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.databinding.ImagePickerItemLayoutBinding

class ImagePickerAdapter : RecyclerView.Adapter<ImagePickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePickerViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ImagePickerItemLayoutBinding.inflate(layoutInflater, parent, false)
        return ImagePickerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagePickerViewHolder, position: Int) {
        holder.bind()
    }


    override fun getItemCount(): Int {
        return 10
    }


}