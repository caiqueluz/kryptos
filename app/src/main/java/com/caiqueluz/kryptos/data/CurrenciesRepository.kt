package com.caiqueluz.kryptos.data

import retrofit2.Response
import javax.inject.Inject

class CurrenciesRepository @Inject constructor(
    private val api: CurrenciesApi
) {

    suspend fun fetchCurrenciesListing(limit: Int): Response<CurrenciesListingDTO> =
        api.fetchCurrenciesListing(limit)

    suspend fun fetchCurrenciesImages(ids: String): Response<CurrenciesImagesDTO> =
        api.fetchCurrenciesImages(ids)
}
