package com.caiqueluz.kryptos

import com.caiqueluz.kryptos.network.OkHttpClientFactory
import com.nhaarman.mockitokotlin2.eq
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

    private val okHttpFactory = OkHttpClientFactory(
        okHttpBuilder = spyOkHttpBuilder
    )

    @Test
    fun whenCreateIsCalled_verifyResponseHasCorrectReadTimeout() {
        okHttpFactory.create()

        verify(spyOkHttpBuilder).readTimeout(
            timeout = eq(30L), unit = eq(TimeUnit.SECONDS)
        )
    }
}
