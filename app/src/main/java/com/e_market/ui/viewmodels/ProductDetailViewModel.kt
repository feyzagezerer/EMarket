package com.e_market.ui.viewmodels

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e_market.data.local.ProductItem
import com.e_market.data.remote.responses.ProductResponse
import com.e_market.data.repository.ProductRepository
import com.e_market.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(var productRepo: ProductRepository): ViewModel() {
    val _productDetail = MutableLiveData<ProductResponse?>()


    fun getProductDetail(id:String)=viewModelScope.launch {
        productRepo.getProduct(id).let {response ->

                _productDetail.postValue(response.data)

        }
    }
        fun deleteProductItem(productItem: ProductItem) = viewModelScope.launch {
            productRepo.deleteProductItem(productItem)
        }
        fun insertProductItem(productItem: ProductItem) = viewModelScope.launch {
            productRepo.insertProductItem(productItem)
        }


}