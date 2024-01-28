package com.e_market.di


import android.content.Context
import androidx.room.Room
import com.e_market.data.local.ProductDatabase
import com.e_market.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    object DatabaseModule {
        @Singleton
        @Provides
        fun provideProductDatabase(
            @ApplicationContext context: Context,
        ) = Room.databaseBuilder(context, ProductDatabase::class.java, DATABASE_NAME).build()


        @Singleton
        @Provides
        fun provideProductDao(
            database: ProductDatabase
        ) = database.productDao()

        @Singleton
        @Provides
        fun provideFavoriteDao(
            database: ProductDatabase
        ) = database.favoriteDao()
    }
