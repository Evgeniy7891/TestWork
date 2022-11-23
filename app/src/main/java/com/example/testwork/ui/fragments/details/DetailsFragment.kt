package com.example.testwork.ui.fragments.details

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ScrollingView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.example.testwork.R
import com.example.testwork.databinding.FragmentDetailsBinding
import com.example.testwork.databinding.FragmentMainBinding
import com.example.testwork.databinding.FragmentSecondBinding
import com.example.testwork.model.details.Details
import com.example.testwork.model.store.BestSeller
import com.example.testwork.model.store.Store
import com.example.testwork.ui.fragments.main.MainAdapter
import com.example.testwork.ui.fragments.main.MainViewModel
import com.google.android.flexbox.AlignContent
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import retrofit2.Response

class SecondFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel.getDetails()
        viewModel.detailList.observe(viewLifecycleOwner, {
            initialImages(it)
            Log.d("TAG", "Observe")
        })


        return binding.root
    }

    private fun initialImages(item: Response<Details>) {
        val images: List<String> = item.body()?.images!!
        Log.d("TAG", "FUN - $images")
//val layoutManager = FlexboxLayoutManager(context).apply {
//    justifyContent = JustifyContent.CENTER
//    alignItems = AlignItems.CENTER
//    flexDirection = FlexDirection.ROW
//    flexWrap = FlexWrap.WRAP
//}
        binding.recyclerviewImageDetails.layoutManager =
         LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val data = images
        val adapter = DetailsAdapter(data)
        binding.recyclerviewImageDetails.adapter = adapter
    }
}