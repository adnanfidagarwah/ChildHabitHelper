package com.example.dummyproject.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.databinding.RepositoryItemDataBinding

class RepositoryViewHolder(
    val binding: RepositoryItemDataBinding?,
    private val adapterOnClick: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding?.root!!) {


}