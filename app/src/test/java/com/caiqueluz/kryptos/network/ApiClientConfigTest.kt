package com.caiqueluz.kryptos.network

import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import retrofit2.Converter
import retrofit2.Retrofit

@RunWith(JUnit4::class)
class ApiClientConfigTest {

    private val mockBaseUrl = "https://mockurl.com"
    private val spyRetrofitBuilder = spy<Retrofit.Builder>()

    private val mockConverterFactory = mock<Converter.Factory>()
    private val mockOkHttpClient = mock<OkHttpClient>()

    private val apiConfig = ApiClientConfig(
        baseUrl = mockBaseUrl,
        retrofitBuilder = spyRetrofitBuilder,
        converterFactory = mockConverterFactory,
        okHttpClient = mockOkHttpClient
    )

    private lateinit var apiClient: Retrofit

    @Before
    fun setup() {
        apiClient = apiConfig.create()
    }

    @Test
    fun whenCreateIsCalled_verifyApiClientHasCorrectBaseUrl() {
        verify(spyRetrofitBuilder).baseUrl(mockBaseUrl)
    }

    @Test
    fun whenCreateIsCalled_verifyApiClientHasCorrectConverterFactory() {
        val expected = true
        val actual = apiClient.converterFactories().contains(
            mockConverterFactory
        )

        assertEquals(expected, actual)
    }

    @Test
    fun whenCreateIsCalled_verifyApiClientHasCorrectOkHttpClient() {
        verify(spyRetrofitBuilder).client(mockOkHttpClient)
    }
}
