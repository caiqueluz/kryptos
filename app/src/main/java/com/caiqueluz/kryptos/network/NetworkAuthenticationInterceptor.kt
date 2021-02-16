package com.caiqueluz.kryptos.network

import okhttp3.Interceptor
import okhttp3.Response

private const val AUTHENTICATION_HEADER = "X-CMC_PRO_API_KEY"

class NetworkAuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader(
            name = AUTHENTICATION_HEADER, value = "WIP_APY_KEY"
        )

        return chain.proceed(request = requestBuilder.build())
    }
}
