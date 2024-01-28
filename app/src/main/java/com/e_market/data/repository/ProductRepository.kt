package com.e_market.data.repository


import androidx.lifecycle.LiveData
import com.e_market.data.local.ProductDao
import com.e_market.data.local.ProductItem
import com.e_market.data.remote.EMarketApi
import com.e_market.data.remote.responses.ProductResponse
import com.e_market.util.Resource
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao,
    private val eMarketAPI: EMarketApi
)  : EMarketRepository{
// for room
    override suspend fun insertProductItem(productItem: ProductItem) {
    productDao.insertProductItem(productItem)
    }

    override suspend fun deleteProductItem(productItem: ProductItem) {
         productDao.deleteProductItem(productItem)
    }

    override  fun observeAllProductItem(): LiveData<List<ProductItem>> {
        return productDao.observeAllProductItem()
    }

   /* override    fun observeTotalPrice(): LiveData<Float> {
        return productDao.observeTotalPrice()
    }*/

    // for api
    override suspend fun getProduct(id: String): Resource<ProductResponse> {
        val response = eMarketAPI.getProduct(id)
        return try {
            val response = eMarketAPI.getProduct(id)
            if (response.isSuccessful) {
                response.body()?.let { productResponse ->
                    return@let Resource.success(productResponse) // Apiden gelen verilerin hepsi başarılı ise bu kod bloğu çalışacak.
                } ?: Resource.error("An unknown error occured.", null)
            } else {
                Resource.error("An unknown error occured.", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
    override suspend fun getAllProducts(): Resource<List<ProductResponse>> {
        return try {
            val response = eMarketAPI.getAllProducts()
            if (response.isSuccessful) {
                response.body()?.let { productResponse ->
                    return@let Resource.success(productResponse)
                } ?: Resource.error("An unknown error occured.", null)
            } else {
                Resource.error("An unknown error occured.", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

}