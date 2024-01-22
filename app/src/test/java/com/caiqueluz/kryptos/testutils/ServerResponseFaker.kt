package com.caiqueluz.kryptos.testutils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer
import java.util.concurrent.TimeUnit
import com.caiqueluz.kryptos.testutils.FakeResponseBody.StringBody
import com.caiqueluz.kryptos.testutils.FakeResponseBody.BufferBody

class ServerResponseFaker : Dispatcher() {

    private val responseMap = mutableMapOf<String, FakeServerResponse>()

    private val emptyResponse =
        FakeServerResponse(
            path = "/",
            code = 404,
            body = null,
            delayInSeconds = 0
        )

    private var currentResponse: FakeServerResponse = emptyResponse

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
                                is BufferBody -> setBody(body.value)
                                is StringBody -> setBody(body.value)
                            }
                        }
                }
                .setBodyDelay(registeredResponse.delayInSeconds, TimeUnit.SECONDS)
        } catch (exception: Exception) {
            exception.printStackTrace()
            MockResponse().setResponseCode(404)
        }

    fun on(path: String): ServerResponseFaker {
        this.currentResponse = currentResponse.copy(path = path)

        return this
    }

    fun answer(code: Int, body: String?): ServerResponseFaker {
        this.currentResponse = currentResponse
            .copy(
                code = code,
                body = body?.let { StringBody(value = it) }
            )

        return this
    }

    fun answer(code: Int, buffer: Buffer): ServerResponseFaker {
        this.currentResponse = currentResponse
            .copy(
                code = code,
                body = BufferBody(value = buffer)
            )

        return this
    }

    fun delay(durationInSeconds: Long): ServerResponseFaker {
        this.currentResponse = currentResponse.copy(delayInSeconds = durationInSeconds)

        return this
    }

    fun register() {
        responseMap[currentResponse.path] = currentResponse
    }
}
