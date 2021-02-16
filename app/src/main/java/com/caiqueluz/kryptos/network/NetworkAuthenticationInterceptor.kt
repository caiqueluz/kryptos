package com.caiqueluz.kryptos.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkAuthenticationInterceptor @Inject constructor(
    private val headerConfig: AuthenticationHeaderConfig
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader(
            name = headerConfig.header, value = headerConfig.value
        )

        return chain.proceed(
            request = requestBuilder.build()
        )
    }
}
