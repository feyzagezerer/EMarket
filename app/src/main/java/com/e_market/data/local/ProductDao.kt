package com.e_market.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductItem(productItem: ProductItem)

    @Delete
    suspend fun deleteProductItem(productItem: ProductItem)

    // Database'den gözlemlenebilir (observable) olarak tüm verileri getir. - Bring all datas from database as observable
    @Query("SELECT * FROM product_items ORDER BY id ASC ")
    fun observeAllProductItem(): LiveData<List<ProductItem>>
/*

    // Database'deki fiyat ve ürün miktarını çarp toplam maliyeti getir - Bring total price, multiplication the price and amount of the product
    @Query("select sum(price * amount) from product_items")
    fun observeTotalPrice(): LiveData<Float>
*/


}