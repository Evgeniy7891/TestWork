package com.example.testwork.ui.fragments.main.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.testwork.R
import com.example.testwork.databinding.FragmentFirstBinding
import com.example.testwork.model.store.Store
import retrofit2.Response

class FirstFragment(private val item: Response<Store>) : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.tvTitleModel.text = item.body()?.home_store?.get(0)?.title
        binding.tvSubtitle.text = item.body()?.home_store?.get(0)?.subtitle
        Glide.with(this)
            .load(item.body()?.home_store?.get(0)?.picture)
            .circleCrop()
            .placeholder(R.drawable.ic_image_search)
            .error(R.drawable.ic_image_search)
            .into(binding.ivPhotoMobile)
        if(item.body()?.home_store?.get(0)?.is_new == true) {
            binding.tvNew.isVisible = true
        }
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}