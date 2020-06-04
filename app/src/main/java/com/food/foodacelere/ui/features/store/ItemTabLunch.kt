package com.food.foodacelere.ui.features.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.food.foodacelere.R
import com.food.foodacelere.ui.features.store.HomeFragment.Companion.PRODUCTS_KEY
import kotlinx.android.synthetic.main.fragment_item_tab.*


class ItemTabLunch : Fragment() {

    lateinit var mAdapter: ProductListAdapter
    lateinit var mProducts: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val listOfProducts = it.getParcelableArrayList<Product>(PRODUCTS_KEY) as ArrayList<Product>
            mProducts = listOfProducts
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        mAdapter = ProductListAdapter(mProducts)
        productList.adapter = mAdapter
        val layoutManager = GridLayoutManager(requireContext(), 2)
        productList.layoutManager = layoutManager


    }

}