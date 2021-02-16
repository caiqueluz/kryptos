package com.caiqueluz.kryptos.network

import retrofit2.Retrofit

class ApiServiceFactory(
    private val retrofit: Retrofit
) {

    fun <T> create(api: Class<T>): T = retrofit.create(api)
}
