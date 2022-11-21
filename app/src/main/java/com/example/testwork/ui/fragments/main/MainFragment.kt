package com.example.testwork.ui.fragments.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils.join
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
                delay(1000)
                initialBestSeller(it)
            }
        })
        binding.ivFilter.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_filtersFragment)
        }
        binding.btnPhones.setOnClickListener {
            //it.backgroundTintList = (ColorStateList.valueOf(Color.parseColor("#FF6E4E")))
            it.backgroundTintList = resources.getColorStateList(R.color.color_scanner)
            binding.btnPhones.setImageResource(R.drawable.ic_phone_white)
            binding.tvPhones.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF6E4E")))

            binding.btnComputer.backgroundTintList = resources.getColorStateList(R.color.white)
            binding.tvComputer.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")))
            binding.btnComputer.setImageResource(R.drawable.ic_computer)

            binding.btnHealth.backgroundTintList = resources.getColorStateList(R.color.white)
            binding.tvHealth.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")))
            binding.btnHealth.setImageResource(R.drawable.ic_health)

            binding.btnBooks.backgroundTintList = resources.getColorStateList(R.color.white)
            binding.tvBooks.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")))
            binding.btnBooks.setImageResource(R.drawable.ic_books)
        }
        binding.btnComputer.setOnClickListener {
            it.backgroundTintList = resources.getColorStateList(R.color.color_scanner)
            binding.tvComputer.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF6E4E")))
            binding.btnComputer.setImageResource(R.drawable.ic_computer_white)

            binding.btnPhones.backgroundTintList = resources.getColorStateList(R.color.white)
            binding.tvPhones.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")))
            binding.btnPhones.setImageResource(R.drawable.ic_phone)

            binding.btnHealth.backgroundTintList = resources.getColorStateList(R.color.white)
            binding.tvHealth.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")))
            binding.btnHealth.setImageResource(R.drawable.ic_health)

            binding.btnBooks.backgroundTintList = resources.getColorStateList(R.color.white)
            binding.tvBooks.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")))
            binding.btnBooks.setImageResource(R.drawable.ic_books)
        }
        binding.btnHealth.setOnClickListener {
            it.backgroundTintList = resources.getColorStateList(R.color.color_scanner)
            binding.tvHealth.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF6E4E")))
            binding.btnHealth.setImageResource(R.drawable.ic_health_white)

            binding.btnComputer.backgroundTintList = resources.getColorStateList(R.color.white)
            binding.tvComputer.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")))
            binding.btnComputer.setImageResource(R.drawable.ic_computer)

            binding.btnPhones.backgroundTintList = resources.getColorStateList(R.color.white)
            binding.tvPhones.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")))
            binding.btnPhones.setImageResource(R.drawable.ic_phone)

            binding.btnBooks.backgroundTintList = resources.getColorStateList(R.color.white)
            binding.tvBooks.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")))
            binding.btnBooks.setImageResource(R.drawable.ic_books)
        }
        binding.btnBooks.setOnClickListener {
            it.backgroundTintList = resources.getColorStateList(R.color.color_scanner)
            binding.tvBooks.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF6E4E")))
            binding.btnPhones.setImageResource(R.drawable.ic_books_white)

            binding.btnComputer.backgroundTintList = resources.getColorStateList(R.color.white)
            binding.tvComputer.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")))
            binding.btnComputer.setImageResource(R.drawable.ic_computer)

            binding.btnHealth.backgroundTintList = resources.getColorStateList(R.color.white)
            binding.tvHealth.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")))
            binding.btnHealth.setImageResource(R.drawable.ic_health)

            binding.btnPhones.backgroundTintList = resources.getColorStateList(R.color.white)
            binding.tvPhones.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")))
            binding.btnPhones.setImageResource(R.drawable.ic_phone)
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
}

