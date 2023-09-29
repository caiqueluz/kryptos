package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.data.CurrenciesApi
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.network.ApiServiceFactory
import org.koin.dsl.module

val dataModule = module {
    single {
        get<ApiServiceFactory>().create(CurrenciesApi::class.java)
    }

    single {
        CurrenciesRepository(
            api = get()
        )
    }
}
