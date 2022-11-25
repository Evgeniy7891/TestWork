package com.example.testwork.ui.fragments.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testwork.R
import com.example.testwork.databinding.FragmentMainBinding
import com.example.testwork.model.store.BestSeller
import com.example.testwork.model.store.Store
import com.example.testwork.ui.fragments.main.pager.PagerAdapter
import kotlinx.coroutines.*
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
        selectIconListener()
        viewModel.getNalMoney()
        viewModel.phonesList.observe(viewLifecycleOwner, {
            initialPager(it)
            CoroutineScope(Dispatchers.Main).launch {
                delay(500)
                initialBestSeller(it)
            }
        })
        binding.ivFilter.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_filtersFragment)
        }


        binding.tvLocal.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment2)
        }

        return binding.root
    }

    private fun initialPager(item: Response<Store>) {
        binding.pagerPhotoBanner.adapter = PagerAdapter(requireActivity(), item)
    }

    private fun initialBestSeller(item: Response<Store>) {
        val bestSeller: List<BestSeller> = item.body()?.best_seller!!
        binding.recyclerview.layoutManager = GridLayoutManager(context, 2)
        val data = bestSeller
        val adapter = MainAdapter(data)
        binding.recyclerview.adapter = adapter
    }


    private fun selectIconListener() {
        with(binding) {
            btnPhones.setOnClickListener {
                it.isSelected = true
                tvPhones.isSelected = true
                btnComputer.isSelected = false
                tvComputer.isSelected = false
                btnHealth.isSelected = false
                tvHealth.isSelected = false
                btnBooks.isSelected = false
                tvBooks.isSelected = false
            }
            btnComputer.setOnClickListener {
                it.isSelected = true
                tvComputer.isSelected = true
                btnPhones.isSelected = false
                tvPhones.isSelected = false
                btnHealth.isSelected = false
                tvHealth.isSelected = false
                btnBooks.isSelected = false
                tvBooks.isSelected = false
            }
            btnBooks.setOnClickListener {
                it.isSelected = true
                tvBooks.isSelected = true
                btnComputer.isSelected = false
                tvComputer.isSelected = false
                btnPhones.isSelected = false
                tvPhones.isSelected = false
                btnHealth.isSelected = false
                tvHealth.isSelected = false
            }
            btnHealth.setOnClickListener {
                it.isSelected = true
                tvHealth.isSelected = true
                btnComputer.isSelected = false
                tvComputer.isSelected = false
                btnBooks.isSelected = false
                tvBooks.isSelected = false
                btnPhones.isSelected = false
                tvPhones.isSelected = false
            }
        }
    }
}

