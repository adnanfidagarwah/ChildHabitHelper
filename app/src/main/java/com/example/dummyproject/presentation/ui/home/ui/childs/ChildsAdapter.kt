package com.example.dummyproject.presentation.ui.home.ui.childs

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.databinding.ChildItemDataBinding

class ChildsAdapter(
    var context: Context,
    private var isEditMode: Boolean,
    private val adapterOnClick: (String, Boolean) -> Unit
) :
    RecyclerView.Adapter<ChildViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ChildItemDataBinding.inflate(layoutInflater, parent, false)
        return ChildViewHolder(binding, adapterOnClick)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {

       /* holder.bind(
            profileList[position],
            position == profileList.size - 1 && profileList.size <= 9 && profileList[profileList.size - 1].name.isNullOrEmpty(),
                    isEditMode
        )*/

        /*  if (position == profileList.size - 1 && profileList.size <= 9) {

             *//* holder.imageview.setImageResource(R.drawable.ic_plus_circle)
            holder.textview?.text = context.getString(R.string.add_profile)*//*

        } else {
            if (isEditMode) {
               *//* holder.imageview.setImageResource(R.drawable.sada)
                holder.imageview.setPadding(0, 0, 0, 0)
                holder.imageview_edit.visibility = View.VISIBLE*//*
            } else {
               *//* holder.imageview_edit.visibility = View.GONE
                holder.imageview.setImageResource(R.drawable.sada)
                holder.imageview.setPadding(0, 0, 0, 0)*//*
            }

        }

      *//*  holder.imageview.setOnClickListener {
            onClick(position)
        }*/
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setEditModeEnable(isEditMode: Boolean) {
        this.isEditMode = isEditMode
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return 6
    }


}