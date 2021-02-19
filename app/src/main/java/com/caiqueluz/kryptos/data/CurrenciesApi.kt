package com.caiqueluz.kryptos.data

import retrofit2.Response
import retrofit2.http.GET

interface CurrenciesApi {

    @GET("/v1/cryptocurrency/info")
    suspend fun fetchCurrencies(): Response<CurrenciesDTO>
}
