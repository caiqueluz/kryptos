package com.caiqueluz.kryptos.network

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val READ_TIMEOUT = 30L

class OkHttpClientFactory(
    private val okHttpBuilder: OkHttpClient.Builder
) {

    fun create(): OkHttpClient = okHttpBuilder
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .build()
}
