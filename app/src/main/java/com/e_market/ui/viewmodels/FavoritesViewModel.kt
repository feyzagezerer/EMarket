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
class FavoritesViewModel @Inject constructor(var productRepo: ProductRepository) : ViewModel() {

    private val _favoriteList = MutableLiveData<List<FavoriteItem>>()
    val favoriteList: LiveData<List<FavoriteItem>> = _favoriteList

    fun loadFavorites(){
        viewModelScope.launch {
            val favorites = productRepo.loadMyFavorites()
            _favoriteList.postValue(favorites)
        }
    }

    fun deleteFavoriteItem(favoriteItem: FavoriteItem) = viewModelScope.launch {
        productRepo.deleteFavoriteItem(favoriteItem)
    }

    fun insertFavorite(favoriteItem: FavoriteItem){
        viewModelScope.launch {
            productRepo.insertFavoriteItem(favoriteItem)
        }
    }


}
