package com.e_market.ui.viewmodels

import androidx.lifecycle.LiveData
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
class HomeViewModel @Inject constructor(var productRepo: ProductRepository) : ViewModel() {

    private val _productList = MutableLiveData<List<ProductResponse>>()
    val productList: LiveData<List<ProductResponse>> = _productList

    private val _text = MutableLiveData("This is home Fragment")
    val text: LiveData<String> = _text

    init {
        getProductList()
    }

    private fun getProductList() = viewModelScope.launch {
        productRepo.getAllProducts().let { response ->
            val productList = response.data!!
            _productList.postValue(productList)
        }
    }

    fun deleteProductItem(productItem: ProductItem) = viewModelScope.launch {
        productRepo.deleteProductItem(productItem)
    }
    fun insertProductItem(productItem: ProductItem) = viewModelScope.launch {
        productRepo.insertProductItem(productItem)
    }
}