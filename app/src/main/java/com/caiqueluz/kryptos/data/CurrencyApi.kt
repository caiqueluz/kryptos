package com.caiqueluz.kryptos.data

import retrofit2.Call
import retrofit2.http.GET

interface CurrencyApi {

    @GET("/wip")
    fun fetchCurrenciesInformation(): Call<CurrencyInformationResponseDTO>
}
