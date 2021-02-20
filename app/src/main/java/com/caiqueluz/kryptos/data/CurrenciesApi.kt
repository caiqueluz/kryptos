package com.caiqueluz.kryptos.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesApi {

    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun fetchCurrenciesListing(
        @Query("limit") limit: Int
    ): Response<CurrenciesListingDTO>

    @GET("/v1/cryptocurrency/info")
    suspend fun fetchCurrenciesImages(
        @Query("id") ids: String
    ): Response<CurrenciesImagesDTO>
}
