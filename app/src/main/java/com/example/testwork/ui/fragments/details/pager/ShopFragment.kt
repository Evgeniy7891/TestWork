package com.example.testwork.ui.fragments.details.pager

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testwork.R
import com.example.testwork.databinding.FragmentFirstBinding
import com.example.testwork.databinding.FragmentShopBinding
import com.example.testwork.model.details.Details
import com.example.testwork.model.store.Store
import retrofit2.Response

class ShopFragment(private val item: Response<Details>) : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        listeners()
        initialShopDetails(item)
        return binding.root
    }

    private fun listeners() {
        binding.fabColorCelectOne.setOnClickListener {
            it.isSelected = true
            binding.fabColorCelectTwo.isSelected = false
        }
        binding.fabColorCelectTwo.setOnClickListener {
            it.isSelected = true
            binding.fabColorCelectOne.isSelected = false
        }
        binding.btMemmoryPhoneOne.setOnClickListener {
            it.isSelected = true
            binding.btMemmoryPhoneTwo.isSelected = false
        }
        binding.btMemmoryPhoneTwo.setOnClickListener {
            it.isSelected = true
            binding.btMemmoryPhoneOne.isSelected = false
        }
    }

    private fun initialShopDetails(item: Response<Details>) {
        with(binding) {
            tvProccesor.setText(item.body()?.CPU)
            tvCamera.setText(item.body()?.camera)
            tvDimm.setText(item.body()?.ssd)
            tvSsd.setText(item.body()?.sd)
            fabColorCelectOne.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor(item.body()?.color?.get(0)))
            fabColorCelectTwo.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor(item.body()?.color?.get(1)))
            btMemmoryPhoneOne.setText(item.body()?.capacity?.get(0))
            btMemmoryPhoneTwo.setText(item.body()?.capacity?.get(1))
        }
    }
}