package com.example.testwork.ui.fragments.details.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapterDetails (fragment: FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return  when(position) {
            0 -> ShopFragment()
            1 -> DetailsPagerFragment()
            else -> FeaturesFragment()
        }
    }
}