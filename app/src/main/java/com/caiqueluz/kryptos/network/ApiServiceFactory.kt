package com.caiqueluz.kryptos.network

import retrofit2.Retrofit

class ApiServiceFactory(
    private val retrofit: Retrofit
) {

    fun <TYPE> create(api: Class<TYPE>): TYPE = retrofit.create(api)
}
