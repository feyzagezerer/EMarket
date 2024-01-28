package com.e_market.data.repository


import androidx.lifecycle.LiveData
import com.e_market.data.local.FavoriteItem
import com.e_market.data.local.ProductItem
import com.e_market.data.remote.responses.ProductResponse
import com.e_market.util.Resource

import retrofit2.Response

interface EMarketRepository {

    // Room Database
    suspend fun insertProductItem(productItem: ProductItem)

    suspend fun deleteProductItem(productItem: ProductItem)

    suspend fun loadMyCart(): List<ProductItem>

    suspend fun update(productItem: ProductItem)

    fun observeAllProductItem(): LiveData<List<ProductItem>>

  //  fun observeTotalPrice(): LiveData<Float>


    // Api
    suspend fun getAllProducts(): Resource<List<ProductResponse>>
    suspend fun getProduct(id: String): Resource<ProductResponse>

    //region favorites
    suspend fun insertFavoriteItem(favoriteItem: FavoriteItem)
    suspend fun deleteFavoriteItem(favoriteItem: FavoriteItem)
    suspend fun loadMyFavorites(): List<FavoriteItem>

    suspend fun loadMyFavorite(id: String): FavoriteItem?
    //endregion
}