package com.example.dummyproject.presentation.ui.home.ui.faqs

import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.databinding.FAQsItemDataBinding

class FAQsViewHolder(
    val binding: FAQsItemDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            binding.expandableLayout.isExpanded = !binding.expandableLayout.isExpanded
        }
    }


    fun bind(faqs: Content) {
        binding.questionTextview.text = faqs.question
        binding.answerTextview.text = faqs.answer
    }
}
