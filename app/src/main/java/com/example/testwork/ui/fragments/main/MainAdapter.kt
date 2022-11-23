package com.example.testwork.ui.fragments.main

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
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

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsBestSeller = mList[position]
        holder.bind(ItemsBestSeller)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(private val binding: CardBestSellerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var like = true
        fun bind(bestSeller: BestSeller) {
            binding.apply {
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
