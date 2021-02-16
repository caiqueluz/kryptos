package com.caiqueluz.kryptos.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val READ_TIMEOUT = 30L

class OkHttpClientFactory @Inject constructor(
    private val okHttpBuilder: OkHttpClient.Builder,
    private val authenticationInterceptor: Interceptor
) {

    fun create(): OkHttpClient = okHttpBuilder
        .addInterceptor(authenticationInterceptor)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .build()
}
