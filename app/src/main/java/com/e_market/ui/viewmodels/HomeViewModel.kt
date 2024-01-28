package com.e_market.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e_market.data.local.FavoriteItem
import com.e_market.data.local.ProductItem
import com.e_market.data.remote.responses.ProductResponse
import com.e_market.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var productRepo: ProductRepository) : ViewModel() {

    private val _productListLiveData = MutableLiveData<List<ProductResponse>>()
    val productListLiveData: LiveData<List<ProductResponse>> = _productListLiveData
    private val _productList = mutableListOf<ProductResponse>()

    private val _text = MutableLiveData("This is home Fragment")
    val text: LiveData<String> = _text

    private val _cartProductCount = MutableLiveData(0)
    val cartProductCount: LiveData<Int> = _cartProductCount
    init {
        getProductList()
    }

    private fun getProductList() = viewModelScope.launch {
        productRepo.getAllProducts().let { response ->
            _productList.clear()
            _productList.addAll(response.data!!)
            _productListLiveData.postValue(_productList)
        }
    }

    fun searchProduct(filter: String){
        if(filter.length < 3){
            _productListLiveData.postValue(_productList)
            return
        }
        viewModelScope.launch {
            val filteredList = _productList.filter { it.brand.contains(filter, true) }
            _productListLiveData.postValue(filteredList.toList())
        }
    }

    private fun loadCartCount(){
        viewModelScope.launch {
            val products = productRepo.loadMyCart()
            _cartProductCount.postValue(products.size)
        }
    }

    fun deleteProductItem(productItem: ProductItem) = viewModelScope.launch {
        productRepo.deleteProductItem(productItem)
    }
    fun insertProductItem(productItem: ProductItem) = viewModelScope.launch {
        productRepo.insertProductItem(productItem)
        loadCartCount()
    }

    private fun deleteFavoriteItem(favoriteItem: FavoriteItem) = viewModelScope.launch {
        productRepo.deleteFavoriteItem(favoriteItem)
    }

    private fun insertFavorite(favoriteItem: FavoriteItem){
        viewModelScope.launch {
            productRepo.insertFavoriteItem(favoriteItem)
        }
    }

    fun onFavoriteClicked(productResponse: ProductResponse){
        viewModelScope.launch {
            val favoriteItem  = FavoriteItem (
                productResponse.price.toFloat(),
                productResponse.model,
                productResponse.brand,
                productResponse.imageUrl,
                productResponse.id.toInt()
            )
            val favorite = productRepo.loadMyFavorite(productResponse.id)
            if(favorite == null){
                insertFavorite(favoriteItem)
            }else{
                deleteFavoriteItem(favoriteItem)
            }
        }
    }
}