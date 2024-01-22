package com.caiqueluz.kryptos.testutils

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule(
    private val faker: ServerResponseFaker
) : TestWatcher() {

    private lateinit var server: MockWebServer

    override fun starting(description: Description) {
        super.starting(description)

        server = MockWebServer().apply {
            dispatcher = faker
            start(8080)
        }
    }

    override fun finished(description: Description) {
        super.finished(description)

        server.shutdown()
    }
}
