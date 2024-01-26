package com.e_market.data.remote


import com.e_market.data.remote.responses.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


// Burada Fotoğrafları search işlemi aratıp karşımıza getirmek için Pixabay'den api sorgusu yaptırdık.

interface EMarketApi {
    @GET("/products")
    suspend fun getProducts(): Response<List<ProductResponse>>


    @GET("/products/")
    suspend fun getProduct(
        @Query("id") id: Int,
    ): Response<ProductResponse>
}