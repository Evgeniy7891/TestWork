package com.example.testwork.ui.fragments.main

import android.os.Bundle
import android.text.TextUtils.join
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testwork.R
import com.example.testwork.databinding.FragmentMainBinding
import com.example.testwork.model.homestore.BestSeller
import com.example.testwork.model.homestore.Store
import com.example.testwork.ui.fragments.pager.PagerAdapter
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.String.join
import kotlin.concurrent.thread

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
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                initialBestSeller(it)
            }
        })
        binding.ivFilter.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_filtersFragment)
        }
        return binding.root
    }


    private fun initialPager(item: Response<Store>) {
        binding.pagerPhotoBanner.adapter = PagerAdapter(requireActivity(), item)
    }

    private fun initialBestSeller(item: Response<Store>) {
        val bestSeller: List<BestSeller> = item.body()?.best_seller!!
        Log.d("TAG", "MAIN - $bestSeller")
        binding.recyclerview.layoutManager = GridLayoutManager(context, 2)
        val data = bestSeller
        val adapter = MainAdapter(data)
        binding.recyclerview.adapter = adapter
    }
}