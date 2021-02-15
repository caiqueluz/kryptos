package com.caiqueluz.kryptos.network

import retrofit2.Converter
import retrofit2.Retrofit

private const val BASE_URL = "WIP"

class ApiClientConfig(
        private val converterFactory: Converter.Factory
) {

    fun create(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
}
