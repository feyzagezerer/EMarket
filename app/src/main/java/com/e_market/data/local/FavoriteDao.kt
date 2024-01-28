package com.e_market.data.local

import androidx.room.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteItem(favoriteItem: FavoriteItem)

    @Delete
    suspend fun deleteFavoriteItem(favoriteItem: FavoriteItem)


    @Query("select * from favorite_items ORDER BY id ASC")
    suspend fun loadMyFavorites(): List<FavoriteItem>

    @Query("select * from favorite_items where id = :id")
    suspend fun loadMyFavorite(id: String): FavoriteItem?

}