package com.caiqueluz.kryptos.network

import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import okhttp3.Interceptor
import okhttp3.Request
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NetworkAuthenticationInterceptorTest {

    private val fakeHeaderConfig = AuthenticationHeaderConfig(
        header = "mockHeader",
        value = "mockValue"
    )

    private val interceptor = NetworkAuthenticationInterceptor(
        headerConfig = fakeHeaderConfig
    )

    private val spyRequestBuilder = spy(
        value = Request.Builder()
            .url("http://mockurl.com")
    )

    private val spyRequest = spy(value = spyRequestBuilder.build()) {
        on { newBuilder() } doReturn spyRequestBuilder
    }

    private val mockChain = spy<Interceptor.Chain> {
        on { request() } doReturn spyRequest
    }

    @Test
    fun whenInterceptIsCalled_verifyResponseHasCorrectHeader() {
        interceptor.intercept(mockChain)

        verify(spyRequestBuilder).addHeader(
            name = eq(fakeHeaderConfig.header),
            value = eq(fakeHeaderConfig.value)
        )
    }
}
