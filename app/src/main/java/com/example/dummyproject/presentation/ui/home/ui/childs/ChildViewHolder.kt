package com.example.dummyproject.presentation.ui.home.ui.childs

import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.data.local.database.child.Child
import com.example.dummyproject.databinding.ProfileItemLayoutBinding

class ChildViewHolder(
    val binding: ProfileItemLayoutBinding,
    private val adapterOnClick: (Child) -> Unit
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(child: Child) {
        binding.root.setOnClickListener {
            adapterOnClick(child)
        }
        binding.buttonLayout.heading.text = child.name
        binding.childPic.setImageBitmap(child.profilePhoto)
    }
}

