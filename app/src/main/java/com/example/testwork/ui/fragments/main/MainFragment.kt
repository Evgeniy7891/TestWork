package com.example.testwork.ui.fragments.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
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
        selectIconListener()
        viewModel.getPhones()
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
        binding.recyclerview.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
        }
        updateBadgeCart(2)
        binding.bottomMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.explorer -> Toast.makeText(context, "Explorer", Toast.LENGTH_SHORT).show()
                R.id.cart -> findNavController().navigate(R.id.action_mainFragment_to_cartFragment2)
                R.id.like -> Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show()
                R.id.profile -> Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show()
            }
            true
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


     fun updateBadgeCart(count: Int) {
         binding.bottomMenu.getOrCreateBadge(R.id.cart).number = count
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


