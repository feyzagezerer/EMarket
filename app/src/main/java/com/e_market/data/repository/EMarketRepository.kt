package com.e_market.data.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.e_market.data.local.ProductItem
import com.e_market.data.remote.responses.ProductResponse
import com.e_market.util.Resource

import retrofit2.Response

interface EMarketRepository {

    // Room Database
    suspend fun insertProductItem(productItem: ProductItem)

    suspend fun deleteProductItem(productItem: ProductItem)

    fun observeAllProductItem(): LiveData<List<ProductItem>>

  //  fun observeTotalPrice(): LiveData<Float>


    // Api
    suspend fun getAllProducts(): Resource<List<ProductResponse>>
    suspend fun getProduct(id: Int): Resource<ProductResponse>
}