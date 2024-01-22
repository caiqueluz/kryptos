package com.caiqueluz.kryptos.data

import com.caiqueluz.kryptos.data.dto.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesApi {

    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun fetchCurrenciesListing(
        @Query("limit") limit: Int
    ): CurrenciesListingDTO

    @GET("/v1/cryptocurrency/info")
    suspend fun fetchCurrenciesWithImages(
        @Query("id") ids: String
    ): CurrenciesImagesDTO
}
