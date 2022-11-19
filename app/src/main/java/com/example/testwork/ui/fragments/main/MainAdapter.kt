package com.example.testwork.ui.fragments.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testwork.R
import com.example.testwork.databinding.CardBestSellerItemBinding
import com.example.testwork.model.homestore.BestSeller

class MainAdapter(private val mList: List<BestSeller>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("TAG", "onCreated!!!")
        val binding = CardBestSellerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsBestSeller = mList[position]
        Log.d("TAG", "nBind - ${ItemsBestSeller.title}")
        holder.bind(ItemsBestSeller)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(private val binding: CardBestSellerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bestSeller: BestSeller) {
            Log.d("TAG", "VIEWHOLDER - ${bestSeller.title}")
            binding.apply {
                tvPrice.text = bestSeller.price_without_discount.toString()
                tvBrand.text = bestSeller.title
                Glide.with(ivPhotoBestSeller)
                    .load(bestSeller.picture)
                    .timeout(1000)
                    .into(ivPhotoBestSeller)
            }
        }
    }
}
