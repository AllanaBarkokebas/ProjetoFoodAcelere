package com.example.task.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class PageAdapter(fm: FragmentManager):FragmentPagerAdapter(fm){

    private var mFragmentList:ArrayList<Fragment> = arrayListOf()
    private var mFragmentTitleList:ArrayList<String> = arrayListOf()


    override fun getItem(position: Int): Fragment = mFragmentList[position]

    override fun getCount(): Int = mFragmentList.size

    fun addFragment(mFragment: Fragment,title:String){
        mFragmentList.add(mFragment)
        mFragmentTitleList.add(title)
    }




    override fun getPageTitle(position: Int): String = mFragmentTitleList[position]


}