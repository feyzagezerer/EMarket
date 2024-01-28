package com.e_market.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e_market.R
import com.e_market.data.remote.responses.ProductResponse
import com.e_market.util.Constants.IMAGE_MAIN_URL


class HomeAdapter(private val dataSet:List<ProductResponse>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val price: TextView
        val brandAndModel: TextView
        val productImage: ImageView

        init {
            price = view.findViewById(R.id.price)
            brandAndModel = view.findViewById(R.id.brandAndModel)
            productImage = view.findViewById(R.id.productImage)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_product, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.price.text = dataSet[position].price.toString()
        viewHolder.brandAndModel.text = String.format("%s %s", dataSet[position].brand, dataSet[position].model)


        val url = dataSet[position].imageUrl
        viewHolder.itemView.apply {
            Glide.with(this).load(url).into(viewHolder.productImage)
        }

        viewHolder.itemView.setOnClickListener {

            val bundle = Bundle()
            bundle.putSerializable("productId",""+dataSet.get(position).id)
            val navigationController = Navigation.findNavController(viewHolder.itemView)
            navigationController.navigate(R.id.action_navigation_home_to_navigation_product_detail,bundle!!)
        }
    }


}