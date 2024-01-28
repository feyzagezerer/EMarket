package com.e_market.ui.views.ui

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e_market.R
import com.e_market.databinding.FragmentHomeBinding
import com.e_market.ui.HomeAdapter
import com.e_market.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class HomeFragment : Fragment() {


    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HomeAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Search"
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Kullanıcı arama yapmayı tamamladığında gerçekleşir
                // Bu noktada arama sonuçlarını işleyebilirsiniz
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Arama metni değiştikçe gerçekleşir
                // Bu noktada otomatik tamamlama veya canlı arama işlemleri yapabilirsiniz
                return true
            }

        }
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        provideViewModel()
        getProducts()
    }
    private fun provideViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }
    private fun getProducts() {
        viewModel._productList.observe(viewLifecycleOwner, Observer {
            adapter = HomeAdapter(it)
            val layoutManager = GridLayoutManager(context, 2) // İkinci parametre sütun sayısını belirler
            binding.listProductRV.layoutManager = layoutManager
            binding.listProductRV.adapter = adapter
        })
    }


}