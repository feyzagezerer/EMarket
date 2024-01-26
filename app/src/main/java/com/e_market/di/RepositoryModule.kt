package com.e_market.di

import com.e_market.data.local.ProductDao
import com.e_market.data.remote.EMarketApi
import com.e_market.data.repository.EMarketRepository
import com.e_market.data.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideProductRepository(
        dao: ProductDao,
        api: EMarketApi
    ) = ProductRepository(dao, api) as EMarketRepository
}

