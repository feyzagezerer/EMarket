package com.e_market.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.e_market.data.remote.responses.ProductResponse

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductItem(productItem: ProductItem)

    @Delete
    suspend fun deleteProductItem(productItem: ProductItem)
  @Query("SELECT * FROM product_items ORDER BY id ASC ")
    fun observeAllProductItem(): LiveData<List<ProductItem>>


    @Query("select * from product_items")
    suspend fun loadMyCart(): List<ProductItem>
    @Update
    suspend fun update(productItem: ProductItem)


}