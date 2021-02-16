package com.caiqueluz.kryptos.data

import retrofit2.Call
import retrofit2.http.GET

interface CurrenciesApi {

    @GET("/v1/cryptocurrency/info")
    fun fetchCurrenciesInformation(): Call<CurrenciesInformationDTO>
}
