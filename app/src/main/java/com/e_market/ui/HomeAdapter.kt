package com.e_market.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e_market.R
import com.e_market.data.local.ProductItem
import com.e_market.data.remote.responses.ProductResponse
import com.e_market.util.Constants.IMAGE_MAIN_URL


class HomeAdapter(
    private val dataSet: List<ProductResponse>,
    private val _onClicked: (String) -> Unit,
    private val _addToChartClicked: (ProductResponse) -> Unit
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val price: TextView
        val brandAndModel: TextView
        val productImage: ImageView
        val addToCart: AppCompatButton

        init {
            price = view.findViewById(R.id.price)
            brandAndModel = view.findViewById(R.id.brandAndModel)
            productImage = view.findViewById(R.id.productImage)
            addToCart = view.findViewById(R.id.addToCart)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_product, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val response = dataSet[position]
        viewHolder.price.text = response.price.toString()
        viewHolder.brandAndModel.text =
            String.format("%s %s", response.brand, dataSet[position].model)


        val url = response.imageUrl
        viewHolder.itemView.apply {
            Glide.with(this).load(url).into(viewHolder.productImage)
        }

        viewHolder.addToCart.setOnClickListener {
            _addToChartClicked.invoke(response)
        }

        viewHolder.itemView.setOnClickListener {

            _onClicked.invoke(response.id)

        }
    }


}