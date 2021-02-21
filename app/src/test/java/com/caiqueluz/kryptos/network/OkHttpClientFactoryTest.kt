package com.caiqueluz.kryptos.network

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import okhttp3.OkHttpClient
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class OkHttpClientFactoryTest {

    private val spyOkHttpBuilder = spy<OkHttpClient.Builder>()
    private val mockInterceptor = mock<NetworkAuthenticationInterceptor>()

    private val okHttpFactory = OkHttpClientFactory(
        okHttpBuilder = spyOkHttpBuilder,
        authenticationInterceptor = mockInterceptor,
        httpLoggingInterceptor = mock()
    )

    @Test
    fun whenCreateIsCalled_verifyResponseHasCorrectReadTimeout() {
        okHttpFactory.create()

        verify(spyOkHttpBuilder).readTimeout(
            timeout = eq(30L), unit = eq(TimeUnit.SECONDS)
        )
    }

    @Test
    fun whenCreateIsCalled_verifyResponseHasAuthenticationInterceptor() {
        okHttpFactory.create()

        verify(spyOkHttpBuilder).addInterceptor(
            interceptor = mockInterceptor
        )
    }
}
