package com.example.testwork.ui.fragments.main.filters

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.testwork.R
import com.example.testwork.databinding.FragmentFiltersBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FiltersFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFiltersBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFiltersBinding.inflate(inflater, container, false)
        binding.ivClose.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}