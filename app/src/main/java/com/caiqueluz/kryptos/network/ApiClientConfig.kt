package com.caiqueluz.kryptos.network

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

private const val BASE_URL = "WIP"

class ApiClientConfig(
        private val converterFactory: Converter.Factory,
        private val okHttpClient: OkHttpClient
) {

    fun create(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
}