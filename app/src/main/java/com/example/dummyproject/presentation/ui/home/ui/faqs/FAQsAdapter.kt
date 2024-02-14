package com.example.dummyproject.presentation.ui.home.ui.faqs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.databinding.FAQsItemDataBinding

class FAQsAdapter(private val faqs: ArrayList<Content>) : RecyclerView.Adapter<FAQsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQsViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FAQsItemDataBinding.inflate(layoutInflater, parent, false)
        return FAQsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FAQsViewHolder, position: Int) {
        holder.bind(faqs[position])
    }


    override fun getItemCount(): Int {
        return faqs.size
    }
}