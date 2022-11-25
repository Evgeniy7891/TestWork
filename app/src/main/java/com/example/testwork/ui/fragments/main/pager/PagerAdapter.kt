package com.example.testwork.ui.fragments.main.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testwork.model.store.Store
import retrofit2.Response

class PagerAdapter(fragment: FragmentActivity, private val item: Response<Store>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment(item)
            1 -> SecondFragment(item)
            else -> ThirdFragment(item)
        }
    }
}
