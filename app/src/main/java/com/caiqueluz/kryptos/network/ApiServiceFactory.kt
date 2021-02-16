package com.caiqueluz.kryptos.network

class ApiServiceFactory(
    private val clientConfig: ApiClientConfig
) {

    fun <T> create(api: Class<T>): T {
        val client = clientConfig.create()
        return client.create(api)
    }
}
