package com.example.testwork.ui.fragments.main.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.testwork.databinding.FragmentSecondBinding
import com.example.testwork.model.store.Store
import retrofit2.Response

class SecondFragment(private val item: Response<Store>) : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.tvTitleModel.text = item.body()?.home_store?.get(1)?.title
        binding.tvSubtitle.text = item.body()?.home_store?.get(1)?.subtitle
        Glide.with(this)
            .load(item.body()?.home_store?.get(1)?.picture)
            .into(binding.ivPhotoMobile)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}