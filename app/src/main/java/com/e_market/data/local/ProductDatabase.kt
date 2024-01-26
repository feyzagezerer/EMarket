package com.e_market.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProductItem::class],
    version = 1
)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao
}