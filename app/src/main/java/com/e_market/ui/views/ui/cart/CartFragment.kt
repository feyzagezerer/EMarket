package com.e_market.ui.views.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.e_market.data.local.ProductItem
import com.e_market.databinding.FragmentCartBinding
import com.e_market.ui.viewmodels.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment @Inject constructor() : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CartViewModel by viewModels()

    private lateinit var adapter: MyCartAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        setupViews()
        observeChanges()
        return binding.root
    }

    private fun setupViews() {
        adapter = MyCartAdapter(this::onMinusClicked, this::onPlusClicked)
        binding.myCartRV.adapter = adapter
    }

    private fun observeChanges() {
        viewModel.apply {
            productList.observe(viewLifecycleOwner) {
                adapter.submitList(it.toList())
            }
            totalAmount.observe(viewLifecycleOwner){
                val total = it.toString() + "₺"
                binding.myCartTotalPriceTv.text = total
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadMyCArt()
    }

    private fun onMinusClicked(productItem: ProductItem) {
        viewModel.decreaseAmount(productItem)
    }

    private fun onPlusClicked(productItem: ProductItem) {
        viewModel.increaseAmount(productItem)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}