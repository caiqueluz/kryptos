package com.caiqueluz.kryptos.network

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

class ApiClientConfig(
    private val baseUrl: ApiBaseUrl,
    private val retrofitBuilder: Retrofit.Builder,
    private val converterFactory: Converter.Factory,
    private val okHttpClient: OkHttpClient
) {

    fun create(): Retrofit = retrofitBuilder
        .baseUrl(baseUrl.value)
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()
}
