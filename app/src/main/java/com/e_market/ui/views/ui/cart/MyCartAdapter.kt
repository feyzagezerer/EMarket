package com.e_market.ui.views.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.e_market.data.local.ProductItem
import com.e_market.databinding.ItemMyCartBinding

class MyCartAdapter(
    private val _onMinusClicked: (ProductItem) -> Unit,
    private val _onPlusClicked: (ProductItem) -> Unit
) : ListAdapter<ProductItem, RecyclerView.ViewHolder>(DIFF_UTIL_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductVH(ItemMyCartBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as ProductVH).binding.apply {

            val price = data.price.toString() + "₺"
            myCartProductPrice.text = price
            val brandModelText = data.brand + " " + data.model
            myCartProductBrandModel.text = brandModelText
            myCartProductCount.text = data.amount.toString()

            myCartMinusItem.setOnClickListener {
                _onMinusClicked.invoke(data)
            }

            myCartPlusItem.setOnClickListener {
                _onPlusClicked.invoke(data)
            }
        }
    }

    private class ProductVH(val binding: ItemMyCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<ProductItem>() {
            override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
                return oldItem == newItem
            }

        }
    }


}
