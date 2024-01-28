package com.e_market.ui.views.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e_market.R
import com.e_market.data.local.ProductItem
import com.e_market.data.remote.responses.ProductResponse
import com.e_market.databinding.FragmentHomeBinding
import com.e_market.ui.HomeAdapter
import com.e_market.ui.viewmodels.HomeViewModel
import com.e_market.ui.views.EMarketActivity
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class HomeFragment : Fragment() {


    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Search"
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchProduct(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(requireContext(), newText, Toast.LENGTH_SHORT).show()
                newText?.let { viewModel.searchProduct(it) }
                return true
            }

        }
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProducts()
    }

    private fun getProducts() {
        viewModel.productListLiveData.observe(viewLifecycleOwner, Observer {
            adapter = HomeAdapter(it, this::onItemClicked, this::onAddToCartClicked, this::onFavoriteClicked)
            val layoutManager = GridLayoutManager(context, 2) // İkinci parametre sütun sayısını belirler
            binding.listProductRV.layoutManager = layoutManager
            binding.listProductRV.adapter = adapter
        })
        viewModel.cartProductCount.observe(viewLifecycleOwner){
            if(it > 0){
                (activity as EMarketActivity).showBadge(it)
            }
        }
    }

    private fun onItemClicked(id: String){
        val bundle = Bundle()
        bundle.putSerializable("productId", id)
        findNavController().navigate(R.id.action_navigation_home_to_navigation_product_detail, bundle)
    }

    private fun onAddToCartClicked(productResponse: ProductResponse){
        val productItem  = ProductItem ("apiResponse.id",
            1,
            productResponse.price.toFloat() ,
            productResponse.model,
            productResponse.brand,
            productResponse.imageUrl,
            productResponse.id.toInt()
        )

        viewModel.insertProductItem(productItem)
    }

    private fun onFavoriteClicked(response: ProductResponse){
        viewModel.onFavoriteClicked(response)
    }


}