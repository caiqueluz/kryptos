package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.data.CurrenciesApi
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.network.ApiServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideCurrenciesApi(
        apiServiceFactory: ApiServiceFactory
    ): CurrenciesApi = apiServiceFactory.create(CurrenciesApi::class.java)

    @Provides
    fun provideCurrenciesRepository(
        api: CurrenciesApi
    ): CurrenciesRepository = CurrenciesRepository(api)
}
