package com.caiqueluz.kryptos.testutils

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule : TestWatcher() {

    private lateinit var server: MockWebServer

    val responseDispatcher = ServerResponseDispatcher()

    override fun starting(description: Description) {
        super.starting(description)

        server = MockWebServer().apply {
            dispatcher = responseDispatcher
            start(8080)
        }
    }

    override fun finished(description: Description) {
        super.finished(description)

        responseDispatcher.reset()
        server.shutdown()
    }
}
