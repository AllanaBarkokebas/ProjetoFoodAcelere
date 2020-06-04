package com.food.foodacelere.ui.features.store

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.food.foodacelere.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product_container.view.*

class ProductListAdapter(var products: ArrayList<Product>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_container,parent,false)
        return mViewHolder(view)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = products[position]
        val productTitle = holder.itemView.itemProductTitle
        val productPrice = holder.itemView.itemProductPrice
        val productImage = holder.itemView.itemPhoto

        productTitle.text = product.title
        productPrice.text = product.price

        if (product.image != ""){

            Picasso.get().load(product.image).into(productImage)
        }



    }


    class mViewHolder(view:View) : RecyclerView.ViewHolder(view)

}