package com.example.testwork.ui.fragments.pager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testwork.R
import com.example.testwork.databinding.FragmentFirstBinding
import com.example.testwork.databinding.FragmentMainBinding
import com.example.testwork.model.homestore.Store
import com.example.testwork.ui.fragments.main.MainViewModel
import kotlinx.coroutines.delay
import retrofit2.Response
import kotlin.concurrent.thread

class FirstFragment(private val item: Response<Store>) : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.tvTitleModel.text = item.body()?.home_store?.get(0)?.title
        Glide.with(this)
            .load(item.body()?.home_store?.get(0)?.picture)
            .circleCrop()
            .into(binding.ivPhotoMobile)
        return binding.root
    }
}