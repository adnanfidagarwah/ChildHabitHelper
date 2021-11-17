package com.example.dummyproject.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.databinding.ProductItemDataBinding
import com.example.dummyproject.ui.list.model.Products
import com.example.dummyproject.util.ListDiffUtil

class ProductAdapter(
    var productsArray: ArrayList<Products> = ArrayList(),
    val adapterOnClick: (Int) -> Unit
) :
    RecyclerView.Adapter<ProductViewHolder>() {

    override fun getItemCount(): Int {
        return productsArray.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductItemDataBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//        holder.setData(educationArrayList[position])
        holder.binding?.product = productsArray[position]
        holder.binding?.root?.setOnClickListener {
            adapterOnClick(productsArray[position].id!!)
        }
        holder.binding?.executePendingBindings()
    }


    fun dataSetChanged(productsArray: ArrayList<Products>) {
        val listDiffUtil =
            ListDiffUtil(this.productsArray, productsArray)
        val diffUtilResult = DiffUtil.calculateDiff(listDiffUtil)
        this.productsArray = productsArray
        diffUtilResult.dispatchUpdatesTo(this)
    }
}