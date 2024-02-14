package com.example.dummyproject.presentation.ui.home.ui.goals

import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.databinding.GoalItemDataBinding

class GoalViewHolder(
    val binding: GoalItemDataBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind() {

//        binding.linearProgressIndicator.progress = 50

        /* if (isLastValue) {

             binding.profileImage.setImageResource(R.drawable.ic_plus_circle)
             binding.profileNameTextView.text = "Add Profile"
         } else {

 //            Utils.loadImageFromUrlWithCoilWithoutCache(binding.profileImage, item)

             binding.profileNameTextView.text = item

 //            binding.profileImage.setPadding(0, 0, 0, 0)

             if (isEditMode)
                 binding.profileImageEdit.visibility = View.VISIBLE
             else
                 binding.profileImageEdit.visibility = View.GONE

         }




         binding.profileImageEdit.setOnClickListener {
             adapterOnClick(item, isLastValue)
         }

         binding.profileImage.setOnClickListener {
 //            if (isLastValue)
             adapterOnClick(item, isLastValue)
         }*/
    }
}

/*
override fun onClick(position: Int) {
    if (position == profileList.size - 1) {
      Util.showToast("Clicked on Add Profile")

    } else {
        Util.showToast("Clicked on Profile")
    }
}*/
