package com.example.testwork.ui.fragments.main

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testwork.R
import com.example.testwork.databinding.CardBestSellerItemBinding
import com.example.testwork.model.store.BestSeller

class MainAdapter(private val mList: List<BestSeller>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardBestSellerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsBestSeller = mList[position]
        holder.bind(ItemsBestSeller)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(private val binding: CardBestSellerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var like = true
        fun bind(bestSeller: BestSeller) {
            binding.apply {
                ivPhotoBestSeller.setOnClickListener {
                    Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_detailsFragment).onClick(it)
                    //it.findNavController().navigate(R.id.action_mainFragment_to_secondFragment2)
                }
                tvPrice.text = "$" + bestSeller.price_without_discount.toString()
                tvBrand.text = bestSeller.title
                tvDiscont.text = "$" + bestSeller.discount_price.toString()
                tvDiscont.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                ivLike.setOnClickListener {
                    if (like == true) {
                        binding.ivLike.setImageResource(R.drawable.ic_selected_like_best_seller)
                        like = false
                    } else {
                        binding.ivLike.setImageResource(R.drawable.ic_like_best_seller)
                        like = true
                    }
                }
                Glide.with(ivPhotoBestSeller)
                    .load(bestSeller.picture)
                    .timeout(500)
                    .into(ivPhotoBestSeller)
            }
        }
    }
}
