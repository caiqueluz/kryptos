package com.caiqueluz.kryptos.data

import com.caiqueluz.kryptos.data.dto.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingDTO

class CurrenciesRepository(
    private val api: CurrenciesApi
) {

    suspend fun fetchCurrenciesListing(limit: Int): CurrenciesListingDTO =
        api.fetchCurrenciesListing(limit)

    suspend fun fetchCurrenciesWithImages(ids: String): CurrenciesImagesDTO =
        api.fetchCurrenciesWithImages(ids)
}
