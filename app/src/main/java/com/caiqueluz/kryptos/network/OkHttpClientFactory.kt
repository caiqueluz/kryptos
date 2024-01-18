package com.caiqueluz.kryptos.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 30L

class OkHttpClientFactory(
    private val okHttpBuilder: OkHttpClient.Builder,
    private val authenticationInterceptor: NetworkAuthenticationInterceptor,
    private val httpLoggingInterceptor: HttpLoggingInterceptor
) {

    fun create(): OkHttpClient = okHttpBuilder
        .addInterceptor(authenticationInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()
}
