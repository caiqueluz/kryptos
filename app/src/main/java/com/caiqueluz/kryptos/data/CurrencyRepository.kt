package com.caiqueluz.kryptos.data

import retrofit2.Call

class CurrencyRepository(
    private val api: CurrencyApi
) {

    fun fetchCurrenciesInformation(): Call<CurrencyInformationResponseDTO> =
        api.fetchCurrenciesInformation()
}
