package com.e_market.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e_market.data.local.ProductItem
import com.e_market.data.remote.responses.ProductResponse
import com.e_market.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavoritesViewModel @Inject constructor(var productRepo: ProductRepository): ViewModel(){
        val _productDetail = MutableLiveData<ProductResponse?>()


        fun getAllProducts()= productRepo.observeAllProductItem()

        fun deleteProductItem(productItem: ProductItem) = viewModelScope.launch {
            productRepo.deleteProductItem(productItem)
        }
        fun insertProductItem(productItem: ProductItem) = viewModelScope.launch {
            productRepo.insertProductItem(productItem)
        }


    }
