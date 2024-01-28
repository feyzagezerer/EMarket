package com.e_market.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e_market.data.remote.responses.ProductResponse
import com.e_market.data.repository.ProductRepository
import com.e_market.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var productRepo: ProductRepository): ViewModel() {

    val _productList = MutableLiveData<List<ProductResponse>>()

    var viewModelTotal = MutableLiveData(0)

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    init {
       getProductList()
    }
    fun getProductList()=viewModelScope.launch {
        productRepo.getAllProducts().let {response ->

            val productList = response.data!!
                _productList.postValue(productList)

    }

    }

}