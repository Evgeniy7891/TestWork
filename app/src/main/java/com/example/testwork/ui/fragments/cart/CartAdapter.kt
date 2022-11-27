package com.example.testwork.ui.fragments.cart


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testwork.R
import com.example.testwork.databinding.CardCartBinding
import com.example.testwork.model.cart.Basket


class CartAdapter(private val mList: List<Basket>) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("TAG", "onBIND")
        val itemBasket = mList[position]
        holder.bind(itemBasket)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(private val binding: CardCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(basket: Basket) {
            Log.d("TAG", "BIND")
            binding.apply {
                tvBrandName.setText(basket.title)
                tvPrice.setText("$"+basket.price.toString())
                Glide.with(ivPhoto)
                    .load(basket.images)
                    .placeholder(R.drawable.ic_image_search)
                    .error(R.drawable.ic_image_search)
                    .timeout(500)
                    .into(ivPhoto)
            }
        }
    }
}