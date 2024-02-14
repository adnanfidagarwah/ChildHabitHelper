package com.example.dummyproject.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.example.dummyproject.data.local.database.child.Child

class ItemsDiffUtil(
    private val oldList: List<Child>,
    private val newList: List<Child>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}