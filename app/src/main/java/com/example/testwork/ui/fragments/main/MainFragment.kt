package com.example.testwork.ui.fragments.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.testwork.R
import com.example.testwork.databinding.FragmentMainBinding
import com.example.testwork.model.homestore.Store
import com.example.testwork.ui.fragments.pager.PagerAdapter
import retrofit2.Response

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        //  val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getNalMoney()
        viewModel.phonesList.observe(viewLifecycleOwner, {
            initialPager(it)
        })
        return binding.root
    }

    private fun initialPager(item: Response<Store>) {
        binding.pagerPhotoBanner.adapter = PagerAdapter(requireActivity(), item)
    }
}