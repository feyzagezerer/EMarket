package com.e_market.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e_market.data.local.FavoriteItem
import com.e_market.databinding.ItemFavoritesProductBinding

class FavoritesAdapter : ListAdapter<FavoriteItem, RecyclerView.ViewHolder>(DIFF_UTIL_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductVH(ItemFavoritesProductBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as ProductVH).binding.apply {
            price.text = data.price.toString()
            val brandModelText = data.brand + " " + data.model
            brandAndModel.text = brandModelText
            val url = data.imageURL
            Glide.with(root).load(url).into(productImage)
        }
    }

    private class ProductVH(val binding: ItemFavoritesProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<FavoriteItem>() {
            override fun areItemsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
                return oldItem == newItem
            }

        }
    }


}
