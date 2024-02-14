package com.example.dummyproject.presentation.ui.home.ui.childs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.data.local.database.child.Child
import com.example.dummyproject.databinding.ProfileItemLayoutBinding

class ChildAdapter (private val childList:List<Child>,
                    private val adapterOnClick: (Child) -> Unit): RecyclerView.Adapter<ChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProfileItemLayoutBinding.inflate(layoutInflater, parent, false)
        return ChildViewHolder(binding,adapterOnClick)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.bind(childList[position])
    }


    override fun getItemCount(): Int {
        return childList.size
    }


}