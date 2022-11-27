package com.example.testwork.ui.fragments.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testwork.R
import com.example.testwork.databinding.ItemDetailsImageBinding


class DetailsAdapter(private val mList: List<String>) : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDetailsImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsImages = mList[position]
        holder.bind(ItemsImages)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(private val binding: ItemDetailsImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(images: String) {
            binding.apply {
                Glide.with(ivImage)
                    .load(images)
                    .placeholder(R.drawable.ic_image_search)
                    .error(R.drawable.ic_image_search)
                    .timeout(500)
                    .into(ivImage)
            }
        }
    }
}
