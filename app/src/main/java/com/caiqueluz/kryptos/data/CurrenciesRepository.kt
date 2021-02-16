package com.caiqueluz.kryptos.data

import retrofit2.Call
import javax.inject.Inject

class CurrenciesRepository @Inject constructor(
    private val api: CurrenciesApi
) {

    fun fetchCurrenciesInformation(): Call<CurrenciesInformationDTO> =
        api.fetchCurrenciesInformation()
}
