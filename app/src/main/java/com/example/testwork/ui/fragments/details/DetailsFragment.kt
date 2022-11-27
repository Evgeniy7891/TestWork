package com.example.testwork.ui.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testwork.R
import com.example.testwork.databinding.FragmentDetailsBinding
import com.example.testwork.model.details.Details
import com.example.testwork.ui.fragments.details.pager.PagerAdapterDetails
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel.getDetails()
        listeners()
        viewModel.detailList.observe(viewLifecycleOwner, {
            initialPager(it)
            CoroutineScope(Dispatchers.Main).launch {
                delay(200)
                initialImages(it)
                delay(200)
                initialBrandName(it)
            }
        })
        CoroutineScope(Dispatchers.Main).launch {
            delay(500)
            TabLayoutMediator(binding.tabDetails, binding.pagerDetails) { tab, position ->
                when (position) {
                    0 -> tab.text = "Shop"
                    1 -> tab.text = "Details"
                    2 -> tab.text = "Features"
                }
            }.attach()
        }
        return binding.root
    }

    private fun initialImages(item: Response<Details>) {
        val images: List<String> = item.body()?.images!!
        binding.recyclerviewImageDetails.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val data = images
        val adapter = DetailsAdapter(data)
        binding.recyclerviewImageDetails.adapter = adapter
    }

    private fun listeners(){
        binding.fabLike.setOnClickListener {
            if (it.isSelected == false) {
                it.isSelected = true
            } else {
                it.isSelected = false
            }
        }
        binding.ivToCart.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_cartFragment2)
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_mainFragment)
        }
    }

    private fun initialPager(item: Response<Details>) {
        binding.pagerDetails.adapter = PagerAdapterDetails(requireActivity(), item)
    }

    private fun initialBrandName(item: Response<Details>) {
        binding.tvBrandName.setText(item.body()?.title)
        if (item.body()?.isFavorites == true) {
            binding.fabLike.isSelected = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}