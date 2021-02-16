package com.caiqueluz.kryptos.network

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit

@RunWith(JUnit4::class)
class ApiServiceFactoryTest {

    private val mockRetrofit = mock<Retrofit>()

    private val apiFactory = ApiServiceFactory(
        retrofit = mockRetrofit
    )

    @Test
    fun whenCreateIsCalled_withApiService_shouldBuildApiService() {
        apiFactory.create(TestApi::class.java)

        verify(mockRetrofit).create(eq(TestApi::class.java))
    }

    private interface TestApi
}
