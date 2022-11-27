package com.example.testwork.ui.fragments.details.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testwork.model.details.Details
import retrofit2.Response

class PagerAdapterDetails (fragment: FragmentActivity, private val item: Response<Details>) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return  when(position) {
            0 -> ShopFragment(item)
            1 -> DetailsPagerFragment()
            else -> FeaturesFragment()
        }
    }
}