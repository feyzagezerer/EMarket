package com.e_market.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e_market.data.local.ProductItem
import com.e_market.data.remote.responses.ProductResponse
import com.e_market.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var productRepo: ProductRepository): ViewModel() {

    private val _productList = MutableLiveData<List<ProductItem>>()
    val productList: LiveData<List<ProductItem>> = _productList

    private val _totalAmount = MutableLiveData<Float>()
    val totalAmount: LiveData<Float> = _totalAmount
    fun loadMyCArt(){
        viewModelScope.launch(Dispatchers.IO) {
            val productList = productRepo.loadMyCart()
            var totalAmount = 0f
            productList.forEach {
                totalAmount += it.price * it.amount
            }
            _totalAmount.postValue(totalAmount)
            _productList.postValue(productList)
        }
    }

    fun increaseAmount(productItem: ProductItem){
        viewModelScope.launch(Dispatchers.IO) {
            productItem.amount += 1
            productRepo.update(productItem)
            loadMyCArt()
        }
    }

    fun decreaseAmount(productItem: ProductItem){
        viewModelScope.launch(Dispatchers.IO) {
            if(productItem.amount == 1){
                productRepo.deleteProductItem(productItem)
                loadMyCArt()
                return@launch
            }
            productItem.amount -= 1
            productRepo.update(productItem)
            loadMyCArt()
        }
    }

}