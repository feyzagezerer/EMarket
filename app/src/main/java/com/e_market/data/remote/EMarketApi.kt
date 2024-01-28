package com.e_market.data.remote


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.e_market.data.remote.responses.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EMarketApi {
    @GET("products/")
    suspend fun getAllProducts(): Response<List<ProductResponse>>

    @GET("products/{id}")
    suspend fun getProduct(
        @Path("id") id: String,
    ): Response<ProductResponse>
}