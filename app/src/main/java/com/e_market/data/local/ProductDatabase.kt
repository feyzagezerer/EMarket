package com.e_market.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProductItem::class, FavoriteItem::class],
    version = 1, exportSchema = false
)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun favoriteDao(): FavoriteDao
}