package com.e_market.ui.views.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.e_market.databinding.FragmentProductDetailBinding
import com.e_market.ui.HomeAdapter
import com.e_market.ui.viewmodels.ProductDetailViewModel
import com.e_market.util.Constants.IMAGE_MAIN_URL
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class ProductDetailFragment : Fragment() {


    private var _binding : FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProductDetailViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater,container,false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        val productId: String? = args?.getString("productId","data")

        provideViewModel()
        viewModel.getProductDetail(productId!!)

        provideViewModel()
        getDetailInformations()
    }
    private fun provideViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(ProductDetailViewModel::class.java)
    }

    private fun getDetailInformations() {
        viewModel._productDetail.observe(viewLifecycleOwner, Observer {
            it.let {

                binding.productDescription.text = it?.description
                binding.productBrand.text = it?.brand
                val url=  it?.imageUrl
                Glide.with(this).load(url).into(binding.productDetailImage)
            }
        })
    }

}