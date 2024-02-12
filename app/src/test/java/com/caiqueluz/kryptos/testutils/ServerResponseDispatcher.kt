package com.caiqueluz.kryptos.testutils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.util.concurrent.TimeUnit

class ServerResponseDispatcher : Dispatcher() {

    private val responseMap = mutableMapOf<String, FakeServerResponse>()

    override fun dispatch(request: RecordedRequest): MockResponse =
        try {
            val registeredResponse =
                responseMap[request.path]
                    ?: error("Fake response not registered for request with path: ${request.path}")

            MockResponse()
                .setResponseCode(registeredResponse.code)
                .apply {
                    registeredResponse
                        .body
                        ?.let { body ->
                            when (body) {
                                is FakeResponseBody.BufferBody -> setBody(body.value)
                                is FakeResponseBody.StringBody -> setBody(body.value)
                            }
                        }
                }
                .setBodyDelay(registeredResponse.delayInSeconds, TimeUnit.SECONDS)
        } catch (exception: Exception) {
            exception.printStackTrace()
            MockResponse().setResponseCode(404)
        }

    fun register(response: FakeServerResponse) {
        responseMap[response.path] = response
    }

    fun reset() {
        responseMap.clear()
    }
}
