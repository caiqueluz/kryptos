package com.caiqueluz.kryptos.testutils

import okio.Buffer

data class FakeServerResponse(
    val path: String,
    val code: Int,
    val body: FakeResponseBody?,
    val delayInSeconds: Long
)

sealed class FakeResponseBody {

    data class StringBody(
        val value: String
    ) : FakeResponseBody()

    data class BufferBody(
        val value: Buffer
    ) : FakeResponseBody()
}
