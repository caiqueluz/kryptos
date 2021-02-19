package com.caiqueluz.kryptos.data

import kotlinx.coroutines.delay
import retrofit2.Response
import javax.inject.Inject

class CurrenciesRepository @Inject constructor(
    private val api: CurrenciesApi
) {

    suspend fun fetchCurrencies(): Response<CurrenciesDTO> =
        api.fetchCurrencies()
}
