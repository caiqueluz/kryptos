package com.caiqueluz.kryptos.testutils

import com.caiqueluz.kryptos.testutils.FakeResponseBody.StringBody

class ServerResponseFaker(
    private val dispatcher: ServerResponseDispatcher
) {

    private val emptyResponse =
        FakeServerResponse(
            path = "/",
            code = 404,
            body = null,
            delayInSeconds = 0
        )

    private var currentResponse: FakeServerResponse = emptyResponse

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

    fun delay(durationInSeconds: Long): ServerResponseFaker {
        this.currentResponse = currentResponse.copy(delayInSeconds = durationInSeconds)

        return this
    }

    fun register() {
        dispatcher.register(response = currentResponse)
    }
}
