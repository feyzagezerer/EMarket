package com.e_market.ui.views.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.e_market.data.local.ProductItem
import com.e_market.data.remote.responses.ProductResponse
import com.e_market.databinding.FragmentProductDetailBinding
import com.e_market.ui.HomeAdapter
import com.e_market.ui.viewmodels.ProductDetailViewModel
import com.e_market.util.Constants.IMAGE_MAIN_URL
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class ProductDetailFragment : Fragment() {


    private var _binding : FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private  val viewModel: ProductDetailViewModel by viewModels()

    private var productId: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString("productId")
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProductDetail(productId!!)
        observeChanges()
    }


    private fun observeChanges() {
        viewModel._productDetail.observe(viewLifecycleOwner) {
            setupViews(it)
        }
    }

    private fun setupViews(response: ProductResponse?){
        response?.let {
            val productItem  = ProductItem ("id",
                1,
                it.price.toFloat(),
                it.model,
                it.brand,
                it.imageUrl,
                it.id.toInt()
            )
            binding.apply {
                addToCartDetail.setOnClickListener {
                    viewModel.insertProductItem(productItem)
                }
                val price = it.price + "₺"
                productDetailTotalPriceTv.text = price
                productDescription.text = it.description
                productBrand.text = it.brand
                Glide.with(this@ProductDetailFragment).load(it.imageUrl).into(binding.productDetailImage)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}