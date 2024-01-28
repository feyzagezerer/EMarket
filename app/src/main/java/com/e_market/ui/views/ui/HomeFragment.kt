package com.e_market.ui.views.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            binding.listProductRV.layoutManager = LinearLayoutManager(context)
            binding.listProductRV.adapter = adapter
        })
    }


}